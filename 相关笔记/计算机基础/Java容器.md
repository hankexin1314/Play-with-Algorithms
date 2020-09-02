# 简介

主要分为两类：

- Collection 集合，除了Map以外都实现了这个

![img](https://camo.githubusercontent.com/d1efb1abc3173aa2a607316dda79bea560fe333f/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f696d6167652d32303139313230383232303934383038342e706e67)

![img](https://camo.githubusercontent.com/cd126ae7572489beead8b33f3a26a5a0bb1f288e/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f696d6167652d32303139313230383232343735373835352e706e67)

### Collection

#### 1. Set

- TreeSet：基于红黑树实现，有序，查找效率 logN
- HashSet：基于哈希表实现，无序，查找效率 1
- LinkedHashSet： 有哈希表的速度，且通过双向链表维护了插入顺序

#### 2. List

- ArrayList：动态数组实现，支持**随机访问**，均摊时间复杂度
- Vector：和ArrayList类似，但是**线程安全**
- LinkedList：双向链表实现，可以**快速插入删除**

#### 3. Queue

- LinkedList：也实现了Deque

- PriorityQueue：堆实现，添加和删除复杂度都为 logN

### Map

- TreeMap：基于红黑树实现
- HashMap：基于哈希表实现
- HashTable：线程安全的HashMap，**不应该使用**，应该使用ConcurrentHashMap，效率更高，引入了分段锁
- LinkedHashMap：双向链表来维护元素顺序

# 设计模式

### 迭代器模式

Collection 继承了 Iterable 接口，所以可以使用`for(int num: arraylist)`方式来遍历

### 适配器模式

只能使用包装类

```java
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);
```

# 源码分析

## 一、ArrayList

### 1. 基本信息

- 数组列表，底层实现是**Object[]**，当装载int，long，boolean...时只能装载他们的**包装类**，**线程不安全**

> - 正常使用一般用作查询，所以即使线程不安全也可以用
> - 增删多的话，应该用LinkedList
> - 线程安全的话，应该用Vector

- 相比于LinkedList，支持**随机访问**，但是**插入**和**删除**速度较慢

- 基于数组实现，**默认大小为10**，实现了**RandomAccess 接口**（表明这个集合支持快速随机访问）

```java
private static final int DEFAULT_CAPACITY = 10;
```

### 2. 扩容&插入

- **默认数组大小为10**

> 如果使用无参构造（不指定容量），初始化一个容量为0的数组，只有add时才会初始化容量为10

- 容量不够时，**扩容为1.5倍**（代价高）

> 扩容后，将原数组内容**复制**，然后指针**重新指向**新数组
>
> JDK1.8用**右移1位**代替了除法，提高了一点效率

```java
int newCapacity = oldCapacity + (oldCapacity >> 1);
elementData = Arrays.copyOf(elementData, newCapacity);
```

- 插入同理，首先检查是否需要扩容，将index和它之后的数据向后复制一格，然后**覆盖插入**

### 3. 删除元素

- 调用`System.arraycopy()`将后面元素复制到index位置上，时间复杂度O(N)，代价高
- 不需要缩容，只是将最后一位置为null，交给GC处理

```java
int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
```

- ArrayList增删不一定慢，取决于操作的元素和数组末尾的**距离**

- 所以，ArrayList做**栈**还是很合适的

## 二、 LinkedList

### 1. 基本信息

- **线程不安全**，如果要安全，需要调用Collections类中的synchronizedList方法
- 实现了Deque和List接口
- JDK1.6实现方法是**循环链表**，JDK1.8是**双向链表**
- **遍历速度**慢于ArrayList，因为ArrayList**内存连续**

## 三、HashMap

### 1. 基本信息

- 由**数组 + 链表**组成，存储时，首先计算key的**哈希值**，然后存入对应数组的index中。数组中保存的是链表的头结点，如果有冲突就用链表串起来

> JDK1.8之前是**头插法**——插在链表头(新插进来的数据更有可能被访问)
>
> JDK1.8之后是**尾插法**——插在后边（为什么）
>
> - 扩容时要重新计算哈希，如果是头插，插入顺序是ABC，链表顺序是CBA
> - 扩容后，C到了别的位置，BA还在原index位置上，B先重新插入，此时B扔指着A，然后A重新插入，A头插法，插在B的前边，A也指向B（**环形链表**）
> - 尾插法就不会有这个问题

- 数组默认大小为**16**， JDK1.8后，链表长度**大于等于**8时，会将链表转化为**红黑树**(会先检查数组长度是否小于64，小于的话先**扩容**)，小于6时会转化为**链表**（因为需要遍历链表效率低）

> 在扩容因子为0.75的情况下，单个槽内数据个数为8的概率极低
>

- **线程不安全**，put，get都没有加同步锁

> 上一秒put的值，下一秒get不一定是原值
>
> HashTable线程安全，使用`synchronized`，但是快被淘汰了，不建议使用
>

### 2. 扩容

- 有一个因子f，默认为0.75，当 size > capacity * f 时扩容，例如大于 16 * 0.75 = 12 时扩容
- 扩容为原来的**2倍**，保证是2的倍数
- 扩容消耗极大，需要**重新计算HashCode**，**将数据重新添加到新数组**

> 如果指定的数组容量不为2的n次方，将自动转化为 超过指定容量的最小的2次幂
> 使用2次幂是为了将key**均匀映射**，因为计算hashCode也是用**位运算**

### 3. 拉链法工作原理

```java
HashMap<String, String> map = new HashMap<>();
map.put("K1", "V1");
map.put("K2", "V2");
map.put("K3", "V3");
```

- 新建一个 HashMap，默认大小为 16；
- 插入 <K1,V1> 键值对，先计算 K1 的 hashCode 为 115，使用除留余数法得到所在的桶下标 115%16=3。
- 插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
- 插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> **前面**。

![img](https://camo.githubusercontent.com/a7761ef5ade932ba3513693653ee65bedfa0f5db/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f696d6167652d32303139313230383233353235383634332e706e67)

查找时：

- 计算key的hashCode
- 在链表上顺序查找

### 4. 重写equals为什么要重写hashCode()

- Object 类中有`equals`和`hashCode`方法，这里的`equals`比较**内存地址**是否相同，`hashCode`也是根据地址计算
- 我们在哈希表中查找时，首先计算hashCode，然后去链表或者红黑树中找对应的key，这里用的方法就是`equals`
- 如果不重写很可能出现两个对象`equals`，但是`hashCode`不等，所以必须重写

### 5. 处理哈希冲突的方法

- **拉链法**
- **开放地址**：如果这个位置哈希冲突，根据规则寻找下一个位置，比如说向后遍历下一个位置
- **再哈希**：维护多个哈希函数，如果用第一个函数哈希冲突，用第二个函数再试

## 四、ConcurrentHashMap

### 1. 实现线程安全的几种方法

- 在HashMap上加锁`Collections.synchronizedMap(Map)`

> 调用这个函数传入了Map，还可以传入一个**锁对象**，如果不传，默认使用传入的Map为**锁对象**
>
> 调用后创建一个SynchronizedMap，在之后的操作就全部上锁

- **HashTable**
- **ConcurrentHashMap**  性能和效率高于前者

### 2. HashTable

- 在HashMap的对数据的操作上加锁（synchronized），效率较低
- 不允许**键**或**值**为null，HashMap允许**键**或**值**为null

> HashTable直接抛异常，HashMap会将hashCode设为0

- HashMap扩容是翻倍，HashTable扩容翻倍 + 1
- HashMap迭代器是 fail-fast,   HashTable的是 fail-safe

> **fail-fast**: Java集合中的一种机制，用迭代器遍历一个集合对象时，如果**遍历过程中**对集合对象的内容进行了修改，会抛出Concurrent Modification Exception
>
> **fail-safe**: 遍历过程不是在原集合上，而是在集合的副本上遍历，所以不会抛异常。但是无法保证读到的数据为**最新**的数据
>
> - java.util包都是`fail-fast`
> - fail-fast在遍历时，会维护一个**变量**，如果集合在遍历期间变化会**改变**这个变量值，遍历时会优先将变量值和期待值比较，相同则继续遍历，不同则抛异常。也有可能变量值刚好修改为期待值，所以一般不利用这个特性并发编程。
> - fail-safe 不允许key为null，因为你不知道这个key是**不存在**还是**空**

### 3. JDK1.7

- 数组 + 链表，链表中的节点，**value值**和**next值**都被**volatile**修饰

> 有序性，可见性

- **分段锁**，数组是`Segment`数组，继承自ReentrantLock。当一个线程占用锁访问一个`Segment`时，不会影响到其他的`Segment`(为数组的每个位置分别加锁)

> 数组容量多大，并发量就多大

- **put**: 尝试**自旋**获取锁，尝试次数达上限则**阻塞获取**
- **get**：因为value有volatile修饰，所以直接取即可

### 4. JDK1.8

- 抛弃原有的Segment数组分段锁，使用`CAS + synchronized`保证并发性
- next和value使用volatile修饰，引入红黑树
- **put**: 写入时，如果节点**为空**，用**CAS**写入，**自旋**保证成功；节点**不为空**，利用**synchronized**写入
- **get**: 直接取

## 二、Vector

### 1. 同步

加入了synchronized

```java
public synchronized boolean add(E e) {
    modCount++;
    ensureCapacityHelper(elementCount + 1);
    elementData[elementCount++] = e;
    return true;
}

public synchronized E get(int index) {
    if (index >= elementCount)
        throw new ArrayIndexOutOfBoundsException(index);

    return elementData(index);
}
```

### 2. 扩容

默认扩充为**2倍**，也可以在构造函数中传入参数，指定每次扩充的值

### 3. Vector和ArrayList的区别

- ArrayList默认扩容1.5倍，Vector为2倍
- 因为Vector使用同步，所以速度慢，开销大，**不建议使用**，因为同步可以由程序员自己控制

## 三、LinkedList

### 1. 基本信息

- 基于双向链表实现

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

- 存储了First和Last指针

### 2. addAll(int index, Collection c)

将一个集合插入指定位置

- 检查index是否合法
- toArray方法转化为数组
- 得到前驱和后继节点
- 插入

### 3. LinkedList和ArrayList的区别

- LinkedList 基于双向链表，ArrayLIst基于数组
- ArrayList支持快速随机访问
- LinkedList插入删除不受元素位置影响，且效率高
- LinkedList因为要存储指针，所以内存占用大