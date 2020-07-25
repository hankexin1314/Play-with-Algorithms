既是刷题记录，也是总结，分类汇总一下。

## 注意的点

- 对于float和double 差值的绝对值小于`0.0000001`即可认为相等

## 需要背诵的代码

- partition

```java
private int partition(int[] nums, int l, int r) {

    int val = nums[l];
    int i = l + 1, j = r;
    while(true) {
        while(i <= r && nums[i] < val) i ++;
        while(j >= l && nums[j] > val) j --;
        if(i > j) break;
        swap(nums, i, j);
        i ++;
        j --;
    }
    swap(nums, l, j);
    return j;
}
```



# 题目

#### [03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

**不考虑空间复杂度**：

- 易想到使用HashSet
- 易想到使用数组，boolean数组，因为n有明确的上限

**空间复杂度**要求为 `1`，允许修改原数组：

> 容易忽略的一个点，长度为n的数组，数字在0~n-1范围内
>
> 说明：nums[num]绝对合法
>
> 利用这一点进行原地排序，其实这个算法时间复杂度也比上面两个优秀

```java
public int findRepeatNumber(int[] nums) {
    if(nums == null || nums.length == 0) return -1;
    for(int i = 0; i < nums.length; i++) {
        while(nums[i] != i) {
            int num = nums[i];
            if(nums[num] == num) return num;
            nums[i] = nums[num];
            nums[num] = num;
        }
    }
    return -1;
}
```

#### [04. 二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

- 主要是能想到从左下或者右上开始寻找，而不是从左上或者左下

#### [29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

- 注意边界条件

## 字符串

#### [05. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

#### [19. 正则表达式匹配](https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)

- 是否会有连续的 `*`(假设这里没有)
- 题目不难，要考虑的边界值比较多

#### [20. 表示数值的字符串](https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

#### [37. 序列化二叉树](https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/)

- StringBuilder的用法 `subString`(返回值是String)， `setLength`
- String的 `substring`(注意是小写)  `split`(返回值是字符串数组)
- 字符串转化为数字`Integer.valueOf` `Integer.parseInt`



## 链表

#### [06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

- 易想到使用栈
- 既然可以想到栈，也可以想到递归

> 注意toArray返回的是Object[]，但是不能强转为int[]，所以最好创建个数组挨个赋值。

#### [18. 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

> 原题是给了一个节点，leetcode是给了一个val，思路其实有些相似

- 对于原题给节点的操作，如果允许修改节点值，可以不用找到前缀节点，直接利用赋值覆盖待删除节点，然后删除尾节点即可
- 考虑特殊情况，原链表只有一个节点，待删除节点是尾节点，待删除节点是头节点
- 对于给一个val的情况，也是需要考虑上述几种特殊情况

#### [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

#### [22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

- 快慢指针

#### [24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

#### [*35. 复杂链表的复制](https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

## 树

#### [07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

- 递归实现，不难，但是需要细心边界条件

#### [26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

#### [27. 二叉树的镜像](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/)

#### [28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

- 沟通清楚对于null的情况是输出true还是false

#### [32 - I. 从上到下打印二叉树](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)

#### [ 32 - II. 从上到下打印二叉树 II](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/)

#### [32 - III. 从上到下打印二叉树 III](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)

#### [33. 二叉搜索树的后序遍历序列](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

#### [34. 二叉树中和为某一值的路径](https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

#### [36. 二叉搜索树与双向链表](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)

- 递归要比迭代快一些

### 二叉树的遍历

- 前序遍历

```java
public void preorder(TreeNode head) {
    if(head == null) return;
    System.out.println(head.val);
    preorder(head.left);
    preorder(head.right);
}

public void preorder(TreeNode root) { 
    Deque<TreeNode> stack = new LinkedList<>();
    while(!stack.isEmpty() || root != null) {
        while(root != null) {
            System.out.println(root.val);
            stack.addLast(root);
            root = root.left;
        }
        root = stack.removeLast();
        root = root.right;
    }
}
```

- 中序遍历

```java
public void inorder(TreeNode head) {
    if(head == null) return;
    inorder(head.left);
    System.out.println(head.val);
    inorder(head.right);
}

public void inorder(TreeNode root) { 
    Deque<TreeNode> stack = new LinkedList<>();
    while(!stack.isEmpty() || root != null) {
        while(root != null) {
            stack.addLast(root);
            root = root.left;
        }
        root = stack.removeLast();
        System.out.println(root.val);
        root = root.right;
    }
}
```

- 后序遍历

```java
public void postorder(TreeNode head) {
    if(head == null) return;
    postorder(head.left);
    postorder(head.right);
    System.out.println(head.val);
}

public void postorder(TreeNode root) { 
    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode pre = null;
    while(!stack.isEmpty() || root != null) {
        while(root != null) {
            stack.addLast(root);
            root = root.left;
        }
        root = stack.peekLast();
        if(root.right == null || root.right == pre) {
            System.out.println(root.val);
            pre = root;
            stack.removeLast();
            root = null;
        }
        else
            root = root.right;
    }
}
```



## 栈和队列

#### [09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

#### [30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

#### [*31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

## 动态规划

#### [14- I. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

#### [14- II. 剪绳子 II](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/)

- 要会用贪心的思想证明应该分成2和3

### 斐波那契数列

#### [10- I. 斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

- 注意取模的位置
- 和面试官沟通整数溢出问题
- 是否允许使用辅助空间

#### [10- II. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

## 排序

#### [21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

- 快速排序的思想
- 不要忘记可能出现的indexOutofRange
- 扩展：将条件判断写在单独的函数中，这样可扩展性好

#### [25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

- 归并排序

#### [*剑指 Offer 39. 数组中出现次数超过一半的数字](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)

- 利用快排的partition
- 需要考虑万一数组中没有这个数字的情况，和面试官沟通

##### 票和法

- 有一个数字超过一半，想象投票，和这个数字相同表示投赞同票`+1`，和这个数字不同表示投反对票`-1`
- 最后的所有票数和一定是正数，基于这个思路想出了票和法
- 维护两个变量，一个是当前的**票和**，另一个是当前的**数字**
- 如果票和为0，则更新数字，遇到和数字不同的数减1，遇到和数字相同的数加1，票和为0继续更新数字
- 则到最后票和一定为正，且此时保存的数字就是众数

```java
    public int majorityElement(int[] nums) {
        int x = 0; // 众数
        int votes = 0; // 票和
        for(int i = 0; i < nums.length; i++) {
            if(votes == 0) x = nums[i];
            votes += (nums[i] == x ? 1 : -1);
        }
        return x;
    }
```





### 一般排序算法

- **冒泡排序**

> **过程**：遍历，相邻两个数字无序则交换，一轮过后末尾为最大值，重复此过程
>
> **速度**：无法提前结束，使用交换消耗较大，**稳定**
>
> **改进**：可以加入一个判断这次遍历是否交换的flag，如果一次遍历中都未交换，说明已经有序。交换改为赋值

- 插入排序

>**过程**：保证已经遍历的部分有序，每有一个新的数字进来，通过交换将其放到有序部分的合适的位置
>
>**速度**：对于近乎有序的序列，效率很高，每次比较可以提前结束，**稳定**
>
>**改进**：交换改为赋值

- 选择排序

> **过程**：每次都找最小的，放到最左边
>
> **速度**：优于插入排序，因为比较元素比交换元素省时，**不稳定**，对所有序列一视同仁，既是是有序序列，也不会提前结束
>
> **优化**：每次不光挑最小的，还挑最大的。交换改为赋值

### 高级排序算法

- 归并排序

> **过程**：分治，合并
>
> **速度**：对于近乎有序的数组，慢于插入排序
>
> **优化**：元素个数较少时，使用插入排序。只有当`nums[mid] > nums[mid+1]`才merge

- 快速排序(具体可见排序算法笔记的整理)

## 查找



#### [11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

- 二分查找（注意等于情况的处理，本质上是没什么办法判断，所以缩小搜索空间）

#### [*剑指 Offer 40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

- 优先队列（nlogk）
- 如果可以修改原数组，可以使用partition（平均时间复杂度：n）

> 对于优先队列，特别适合处理**海量数据**。主要是不需要一次性将数据全部加载进内存，而是依次读入
>
> 对于partition，速度上来说要快一些，但是需要修改原数组，而且需要将数据一次性读入

### 回溯法

#### [12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

#### [13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

- 直白的做法，翻译题目中的条件

> - 可以上下左右移动：每到一个点都尝试上下左右去DFS
>
> 位数和不超过k：每到一个点都计算位数和与k比较

- 优化做法，加入自己的思考

> - 自己写一写位数和，会发现从左上到右下递增（整十数有些特殊），没必要上下左右去寻找，只用右下即可
> - 题目明确给出 m，n <= 100，可以用一个数组存储位数和，不需要每次都计算

#### [剑指 Offer 38. 字符串的排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)

- `String.valueOf(char[])`

```java
LinkedList<String> res = new LinkedList<>();
return res.toArray(new String[res.size()]);
```

> 补充几个leetcode的题

#### [46. 全排列](https://leetcode-cn.com/problems/permutations/)

- 方法与38题类似

#### [51. N皇后](https://leetcode-cn.com/problems/n-queens/)

- 套路解题，[套路见](https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban)

#### [77. 组合](https://leetcode-cn.com/problems/combinations/)

- 注意减枝

## 位操作

#### [15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

- 注意可能为负数
- 注意位操作优先级低，多加括号

### 快速幂

#### [16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

- 注意负数问题
- 注意整数溢出，很容易忽略，负数变正数有可能有一个数溢出（-2^31）

## 字符串

#### [*17. 打印从1到最大的n位数](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

- 字符串加法（不太好写）
- 数字排列

```java
public void printNumbers(int n) {
    if(n <= 0) throw new IllegalStateException("n is illegal.");
    char[] num = new char[n];
    Arrays.fill(num, '0');
    helper(num, 0);
}

private void helper(char[] num, int index) {
    if(index == num.length) {
        printNum(num);
        return;
    }
    for(int i = 0; i < 10; i++) {
        num[index] = (char)('0' + i);
        helper(num, index + 1);
    }
}

private void printNum(char[] num) {
    StringBuilder sb = new StringBuilder();
    boolean isZero = true;
    for(int i = 0; i < num.length; i++) {
        if(isZero && num[i] == '0') continue;
        isZero = false;
        sb.append(num[i]);
    }
    if(sb.length() != 0) // 避免打印出0  根据题目要求来作答
        System.out.println(sb.toString());
}
```

