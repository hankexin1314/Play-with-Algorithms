# 大的概念

### 1. JDK与JRE

- JDK是Java Development Kit，Java开发工具，包含JRE和javac编译器之类的东西，可以创建和编异程序
- JRE是Java运行环境，集成了JVM和一些类库，只能运行，不能创建和编译新程序

### 2. 跨平台性

- 一次编译，可在多个系统平台上运行（编译为`.class`文件，哪里有JVM，哪里就可以执行）JVM屏蔽了平台多样性。

### 3. Java与C++的区别

- 都是面向对象的语言，支持封装，继承，多态
- Java不能通过指针访问内存（没有指针），C++可以
- Java不用手动释放内存，C++需要
- Java单继承，但是有接口，C++支持多继承
- Java跨平台，C++依赖特定平台
- C语言**字符串结束符**`\0`，Java不需要，因为Java万物皆对象，既然是对象就有大小，还可以通过length属性指明长度，所以不用。

### 4. Java编译与解释共存

- 高级语言，编译，直接转化为本机可执行的**机器码**，然后执行
- 解释，执行时逐行解释成**机器码**，边运行变解释
- Java多了个JVM，所以要先**编译**，然后由JVM解释称**机器码**

# 关键字

## 1. static

- **变量**：静态变量，类变量

- **方法**：不依赖任何实例，类加载后就存在，不能是抽象方法，只能访问静态变量

- **代码块**：类**初始化**时执行一次`<clinit>()`

- **内部类**

### 1.1 初始化顺序

- 先父类后子类
- 静态代码块和静态变量优先级看二者先后顺序

## 2. final

### 数据

- 声明为常量，编译时会标明`ConstantValue`，类加载的**准备**阶段时赋值
- 对于基本类型，保证值不变
- 对于引用类型，保证引用不变，不能引用其他对象，被引用对象本身可以修改

```java
final int x = 1;
// x = 2;  // cannot assign value to final variable 'x'
final A y = new A();
y.a = 1; // 被引用对象y可以改变其属性值
```

### 方法

- 不能被重写，private方法被隐式定义为final

### 类

- 不能被继承

# 数据类型

## 1. 基本数据类型

boolean/~, byte/8, **char/16**, short/16, int/32, long/64, float/32, double/64

> boolean大小未明确规定，JVM会将boolean转化为int，1/0 true/false
>
> boolean数组使用byte数组实现

### 1.1 包装类型

- 自动**装箱**和**拆箱**

```java
Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
int y = x;         // 拆箱 调用了 x.intValue()
```

### 1.2 缓存池

- Byte，Short，Integer，Long都有[-128, 127]的缓存池
- Character有[0, 127]的缓存池
- Boolean直接返回true或者false

- 调用Integer.valueOf(int)会优先在缓存池中查找，没有再新建

> 缓存池大小根据性能和资源均衡选择的

## 2. String

- 被声明为**final类**，不可被继承，包装类也不可被继承
- 内部使用`char[]`数组存储数据（JDK8），被声明为**final数组**，不可变

### 2.1 不可变得好处

- 缓存**hash code**
- **字符串常量池**
- String经常作为参数，不可变比较**安全**
- 天生具有**线程安全**

### 2.2 String, StringBuffer and StringBuilder

- StringBuilder，StringBuffer可变
- String，StringBuffer（synchronized实现）线程安全，StringBuilder不安全

### 2.3 String Pool

字符串常量池，也就是全局字符串常量池，具体见JVM



# 运算

## 1. 参数传递

Java是值传递而不是引用传递，也就是说，参数传递传递的是参数的拷贝，而不是参数的引用

- 对于基本数据类型

```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    swap(num1, num2); // 传递的是字面量的一份拷贝

    System.out.println("num1 = " + num1); // num1 = 10
    System.out.println("num2 = " + num2); // num2 = 20
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a); // a = 20
    System.out.println("b = " + b);	// b = 10
}
```

- 对于引用类型

```java
public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    System.out.println(arr[0]); // 1
    change(arr); // 传递的是引用的拷贝，同一个引用 所以会改变其中的值
    System.out.println(arr[0]); // 0
}

public static void change(int[] array) {
    array[0] = 0;
}
/////////////////////////////////////////////////////////////////
public static void main(String[] args) {
    Student s1 = new Student("小张");
    Student s2 = new Student("小李");
    Test.swap(s1, s2); // 传递的是引用的拷贝
    System.out.println("s1:" + s1.getName()); // 小张
    System.out.println("s2:" + s2.getName()); // 小李
}

public static void swap(Student x, Student y) {
    Student temp = x; 
    x = y;
    y = temp; // 只改变了引用的拷贝的指向，原引用的指向不会改变
    System.out.println("x:" + x.getName()); // 小李
    System.out.println("y:" + y.getName()); // 小张
}
```

## 2. 隐式类型转换

- +=， ++会自动进行类型转换

- ```java
  // float f = 1.1; 字面量为double，不能隐式向下转换类型
  float f = 1.1f;
  ```

## 3. switch

- Java7之后，switch支持String对象

## 4. equals和==

- 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
- 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。

## 5. 浅拷贝和深拷贝

- 浅拷贝，二者指向同一个对象（用clone()方法）
- 深拷贝，二者指向不同对象，实现复杂。

# 面向对象

## 1. 封装

## 2. 继承

### 2.1 访问权限

- public，类内，包内，子类，包外可见
- protected，类内，包内，子类可见
- 不写，类内，包内可见
- private，类内可见

> 访问权限只有编译时起作用，.class文件中没有访问权限，所以可以用反射访问任何字段

### 2.2 接口和抽象类的区别

- 设计层面上，抽象类需要满足里氏替换原则（子类必须可以替换掉父类对象），接口只是一个方法实现规范
- 类可以实现多个接口，但只能继承一个抽象类
- 接口成员只能是public，抽象类成员可以有多种访问权限
- 接口字段默认是static和final，抽象类可以继承非静态，非常量字段。

### 2.3 重写

- 子类方法**访问权限** >= 父类方法访问权限
- 子类方法**返回类型**，为父类方法返回类型或其子类
- 子类方法抛出的**异常类型**，为父类抛出的异常类型或其子类

> 利用Override方法让编译器检查是否符合要求

## 3. 多态

- **重载**，方法名相同，但是参数类型，个数，顺序至少有一个不同。只有返回值不同不算是重载。

# 反射

## 1. 原理

- JVM加载`.class`文件后，会在内存中生成一个Class对象，来访问这个类的成员
- `Class.forName("com.mysql.jdbc.Driver")`会返回一个Class对象，动态加载

## 2. 优缺点

优点：

- 可扩展性
- 解耦（IOC）

缺点：

- 性能开销：动态解析，无法用JVM优化
- 内部暴露：可以访问私有成员

## 3. IOC的底层原理



# 动态代理



# 版本特性

- JDK8 Spring 内部使用`char[]`数组存储数据， JDK9之后使用`byte[]`，并且内部`coder`属性标明编码方式

## JDK8新特性



### 1. 接口可以有默认的方法实现

- 如果没有维护成本太高，接口新添加一个方法，就需要修改所有使用该接口的代码。
- 接口的**字段和方法**默认是public的，不能被定义为private或protected
- 接口的字段默认是**static**和**final**的