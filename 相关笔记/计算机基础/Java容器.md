## 简介

主要分为两类：

- Collection 集合
- Map 键值对

![img](https://camo.githubusercontent.com/d1efb1abc3173aa2a607316dda79bea560fe333f/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f696d6167652d32303139313230383232303934383038342e706e67)

![img](https://camo.githubusercontent.com/cd126ae7572489beead8b33f3a26a5a0bb1f288e/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f696d6167652d32303139313230383232343735373835352e706e67)

### Collection

#### 1. Set

- TreeSet：基于红黑树实现，有序，查找效率 logN
- HashSet：基于哈希表实现，无序，查找效率 1
- LinkedHashSet： 有哈希表的速度，且通过双向链表维护了插入顺序

#### 2. List

- ArrayList：动态数组实现，支持随机访问，均摊时间复杂度
- Vector：和ArrayList类似，但是线程安全
- LinkedList：双向链表实现，可以快速插入

#### 3. Queue

- LinkedList：也实现了Deque

- PriorityQueue：堆实现，添加和删除复杂度都为 logN

### Map

- TreeMap：基于红黑树实现
- HashMap：基于哈希表实现
- HashTable：线程安全的HashMap，**不应该使用**，应该使用ConcurrentHashMap，效率更高，引入了分段锁
- LinkedHashMap：双向链表来维护元素顺序

## 设计模式

### 迭代器模式

Collection 继承了 Iterable 接口，所以可以使用`for(int num: arraylist)`方式来遍历

### 适配器模式

只能使用包装类

```java
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);
```

## 源码分析

### 一、ArrayList

#### 1. 基本信息

基于数组实现，默认大小为10，支持快速随机访问

```java
private static final int DEFAULT_CAPACITY = 10;
```

#### 2. 扩容

- 容量不够时，扩容为1.5倍
- 使用的方法为`Arrays.copyOf()`，代价高，最好创建时指明大小，避免扩容

```java
int newCapacity = oldCapacity + (oldCapacity >> 1)；
elementData = Arrays.copyOf(elementData, newCapacity);
```

#### 3. 删除元素

- 调用`System.arraycopy()`将后面元素复制到index位置上，时间复杂度O(N)，代价高
- 不需要缩容，只是将最后一位置为null，交给GC处理

```java
int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
```

#### 4. 序列化

### 二、Vector

#### 1. 同步

synchronized

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

#### 2. 扩容

默认扩充为2倍，也可以在构造函数中传入参数，指定每次扩充的值

#### 3. 替代方案

使用 concurrent 并发包下的 CopyOnWriteArrayList 类

```java
List<String> list = new CopyOnWriteArrayList<>();
```

- 读写分离，读在原数组，写在一个复制数组（空间开销大）
- 写需要用lock加锁

- 读不能读到实时性数据

### 三、LinkedList

#### 1. 基本信息

- 基于双向链表实现

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

- 存储了First和Last指针

### 四、 HashMap

#### 1. 基本信息

- 存储着一个Entry数组
- 默认大小为16

```java
transient Entry[] table;

static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    int hash;

    Entry(int h, K k, V v, Entry<K,V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry e = (Map.Entry)o;
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
```

- 从next变量可知，数组中每一个位置都是一个链表。

#### 2. 拉链法工作原理

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

#### 3. put 操作

- 支持key为null，但是因为无法计算hashCode，所以将其放在0号位置
- 对于hashCode相同的值，使用头插法，插在前边

#### 4. 确定桶下标

- 计算hashCode
- 取模（使用的时位运算实现，效率高）

#### 5. 扩容

- 动态扩容，数组长度 M， 存储数量 N， 当size超过threshold就会扩容
- 扩容为原来的2倍（保证是2的n次方）
- 需要将旧键值对插入新的table中，费时
- 扩容后需要计算新的桶下标

#### 6. 指定数组容量

如果指定的数组容量不为2的n次方，将自动转化为 超过指定容量的最小的2次幂

#### 7. 链表转红黑树

JDK1.8之后，桶中链表长度大于8会将其转化为红黑树

