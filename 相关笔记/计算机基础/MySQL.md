## 一、相关背景概念

### 1. 如何从数据库中查找数据（为什么使用 B树）

- 数据库会有索引，索引存在磁盘中，我们通过索引查找数据
- 数据量很多时，索引可能会有几个G，所以我们不可能将索引全部读入内存后从中找到我们需要的数据，我们只能按照一定规则逐一加载磁盘页，然后找到数据

- 索引有很多存储形式，我们假设它以**二叉搜索树**存储（其实并不是

<img src="E:\workspace\Play-with-Algorithms\img\6954572-0951c4a92f287c47.webp" alt="6954572-0951c4a92f287c47" style="zoom:50%;" />

> 假设我们从下面的二叉树索引中查找索引10

<img src="E:\workspace\Play-with-Algorithms\img\6954572-8144d32410741c98.webp" alt="6954572-8144d32410741c98" style="zoom:80%;" />

- 将9读取到内存，索引10 > 9，所以向右找到13
- 将13读取到内存，重复上面步骤，直至找到10
- 总共进行了4次IO —— **IO次数是树的最大深度**
- 因此，我们需要一种数据结构，来减少IO次数，减少最大高度

### 2. B树

对于一个m阶B树，m >= 2（m是每个节点最多分几个叉，二叉树就是m==2）：

> B树可以理解为n叉树，一个节点可以有好多个元素，元素排列方式还是类似平衡二叉树，左小右大
>
> 2-3树其实就是一个3阶B树，有复杂的自平衡措施
>
> m受限于磁盘页的大小，页是内存调度的基本单位

- 根节点至少有2子节点
- 每个中间节点都有 k - 1个元素和k个孩子， m/2 <= k <= m（类似满二叉树，非叶子节点必须每个叉都有孩子
- 每个叶子节点都有 k - 1个元素， m/2 <= k <= m
- **所有叶子节点位于同一层**
- 每层元素从小到大排列（还要遵守左小右大）

### 3. B+树

B树的优化版

- 非叶子节点不存储数据，只保存索引（使得一个磁盘块能保存的索引数大大增加，m上限提高，树高下降
- 叶子节点保存了父节点所有元素的索引
- 相邻叶子结点从左到右有指针相连

优点：

- 因为数据都在叶子结点，叶子结点在同一层，所以每次查找所花费时间相同
- 树高更低，磁盘IO次数减少
- 全数据遍历只需要遍历叶子结点（叶子结点相连），不需要遍历整个树

缺点：

- 如果经常访问的数据在B树中，离根节点近，则访问速度可能超过B+树

#### 查找数据流程

- 在根节点二分查找，找到一个子节点的索引
- 重复二分查找找到叶子节点
- 在叶子节点上二分查找到data

## 二、MySQL索引

索引是存储引擎实现的。

### 分类

#### 1. B+Tree索引

- MySQL引擎默认的索引类型，有序
- 可以指定多个列作为索引
- InnoDB 的 B+Tree 索引分为主索引和辅助索引

> 主索引的叶子结点中存储着完整的数据，又称聚簇索引，一个表中只有一个，因为不能将数据存放在两个地方
>
> 辅助索引的叶子结点存放着主键，拿到主键后再去主索引中找data

```sql
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `age` int(3) unsigned NOT NULL DEFAULT '1' COMMENT '年龄',
  PRIMARY KEY (`id`), # 主键
  KEY `I_name` (`name`) # 键
) ENGINE=InnoDB;
```

这样，会有两个索引表，两棵B+树

<img src="E:\workspace\Play-with-Algorithms\img\20200623171319.png" alt="20200623171319" style="zoom:80%;" />

- 主索引叶子结点存放数据
- 辅助索引叶子结点只有主键值

#### 2. 哈希索引

- 以O(1)速度查询，但是失去了有序性，不能排序，分组
- 无法范围查找，只能精确查找

> InnoDB会在某一个索引被频繁使用时，会在B+Tree索引上创建一个哈希索引

#### 3. 全文索引

查找文本中的关键词，而不是直接比较是否相等

#### 4. 空间数据索引

地理数据的存储

### 索引优化

#### 1. 索引必须是独立的列

索引不能是表达式的一部分，例如下例

```sql
SELECT actor_id FROM sakila.actor WHERE actor_id + 1 = 5;
```

#### 2. 多列索引

条件有多个列时，使用多列索引性能更好

```sql
SELECT film_id, actor_ id FROM sakila.film_actor # 多列索引
WHERE actor_id = 1 AND film_id = 1;   # 条件
```

#### 3. 索引的顺序

选择性最强的索引放前边

例如下面显示的结果中 customer_id 的选择性比 staff_id 更高，因此最好把 customer_id 列放在多列索引的前面。

```sql
SELECT COUNT(DISTINCT staff_id)/COUNT(*) AS staff_id_selectivity,
COUNT(DISTINCT customer_id)/COUNT(*) AS customer_id_selectivity, # 这个放前边更好
COUNT(*)
FROM payment;
```

#### 4. 覆盖索引

> 索引中就包含所有需要查询的字段，性能更好，一个例子：

- 还是上边的例子，我们使用name和age建立联合索引

```sql
ALTER TABLE student DROP INDEX I_name;
ALTER TABLE student ADD INDEX I_name_age(name, age); # 建立联合索引
```

- 索引表变为这样

<img src="E:\workspace\Play-with-Algorithms\img\20200623171845.png" alt="20200623171845" style="zoom:80%;" />

- 查询

```sql
SELECT age FROM student WHERE name = '小李'；
```

- 不会再去查询主索引，直接在联合索引中，找到了年龄12

> 优点：

- 索引通常远小于数据行，减少数据访问量
- InnoDB中，不需要访问主索引

### 索引的优点

- 减少了数据的扫描量（索引要远小于数据行）
- 让服务器避免了分组和排序

## 三、查询性能优化

### 使用explain分析

几个关键的参数

- select_type: 查询的类型，简单查询，联合查询，子查询等
- key: 使用的索引
- rows: 扫描的行数

## 四、存储引擎

### 1. InnoDB

- 默认的事务型存储引擎

- 主索引是聚簇索引

#### 隔离级别

### 2. MyISAM

- 不支持事务
- 

### 比较

