# 大的概念

### 面向对象和面向过程区别

- **面向过程**：性能高，适用于性能优先的场景，比如单片机，嵌入式开发
- **面向对象**：易维护，易复用，易扩展，因为有封装，继承和多态这些特性。性能差，类的实例化调用消耗大量资源。
- JAVA性能差的原因：除了类的实例化外，Java编译的结果不是机器码，而是给JVM的字节码，C/C++编译的结果直接就是机器码，所以效率高。

### JAVA特点

- 面向对象
- 平台无关
- 支持多线程
- 支持网络编程
- 编译与解释共存

### JDK与JRE

- JDK是Java Development Kit，Java开发工具，包含**JRE**和**Javac编译器**之类的东西，可以创建和编异程序
- JRE是Java运行环境，集成了**JVM**和一些**类库**，只能运行，不能创建和编译新程序

### 跨平台性

- 一次编译，可在多个系统平台上运行（编译为`.class`文件，哪里有JVM，哪里就可以执行）JVM屏蔽了平台多样性。

### Java与C++的区别

- 都是面向对象的语言，支持封装，继承，多态
- Java不能通过指针访问内存（没有指针），C++可以
- Java不用手动释放内存，自动管理，C++需要手动管理
- Java单继承，但是有接口，C++支持多继承
- Java跨平台，C++依赖特定平台
- C语言**字符串结束符**`\0`，Java不需要，因为Java万物皆对象，既然是对象就有大小，还可以通过length属性指明长度，所以不用。

### Java编译与解释共存

- 高级语言，编译，直接转化为本机可执行的**机器码**，然后执行
- 解释，执行时逐行解释成**机器码**，边运行变解释
- Java多了个JVM，所以要先**编译**，然后由JVM解释成**机器码**

### 成员变量和局部变量

- 成员变量是类的，局部变量是方法的
- 成员变量在堆中，局部变量在方法栈中
- 成员变量随对象生死，局部变量随方法调用生死
- 成员变量不需要显式赋值，会自动赋默认值，局部变量需要显式赋值

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

- 被声明为**final类**，不可被继承（包装类也不可被继承）
- 内部使用`char[]`数组存储数据（JDK8），被声明为**final数组**，不可变
- JDK9之后使用的`byte[]`

### 2.1 不可变得好处

- 缓存**hash code**
- **字符串常量池**
- String经常作为参数，不可变比较**安全**
- 天生具有**线程安全**

### 2.2 StringBuffer and StringBuilder

- 二者继承自AbstractStringBuilder类，`char[]`，没有**final**修饰符，可变
- StringBuffer（synchronized实现）线程安全，StringBuilder不安全

### 2.3 String Pool

字符串常量池，也就是全局字符串常量池，具体见JVM

### 2.4 性能

- String每次改变都会新建一个String对象，StringBuffer会对对象本身操作
- StringBuffer因为线程安全，比StringBuilder性能差一点

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

#### 重写equals为什么要重写hashCode

- equals确定相等，则hashCode必须相等
- hashCode默认根据对象在堆上位置生成
- 如果不重写，可能出现两个对象equals，但hashCode不等的情况

## 5. 浅拷贝和深拷贝

- 浅拷贝，二者指向同一个对象（用clone()方法）
- 深拷贝，二者指向不同对象，实现复杂。

# 面向对象

## 1. 封装

- 将属性和方法私有化，同时为外界提供一些可以访问的属性和方法

## 2. 继承

- 子类继承父类的全部属性和方法，对于private的，只是拥有，不能访问
- 子类可以**扩展**父类的属性和方法，并且可对父类的方法**重写**

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

> JDK8之前，接口只有常量变量和抽象方法
>
> JDK8引入默认方法和静态方法
>
> JDK9引入私有方法和私有静态方法

### 2.3 重写

- 子类方法**访问权限** >= 父类方法访问权限
- 子类方法**返回类型**，为父类方法返回类型或其子类
- 子类方法抛出的**异常类型**，为父类抛出的异常类型或其子类

> 利用Override方法让编译器检查是否符合要求

> 重载和重写
>
> - **重载**：同一个类中，**参数顺序**，**数量**，**类型**，**返回值**，**访问修饰符**有一个不同，**名称**相同
> - **重写**：父类，子类，子类对父类方法重写，上面说的几项必须相同

### 2.4 定义⼀个不做事且没有参数的构造⽅法的作用

- 执行子类的构造方法前，会优先调用父类的构造方法
- 如果没有显式地通过`super()`调用，则自动调用无参构造方法
- 如果没有定义任何构造方法，则会自动生成一个无参构造方法，在调用该类时自动执行
- 如果父类只定义了有参构造方法，且子类没有通过`super()`调用构造方法，则编译时报错

## 3. 多态

- **多态**：引用变量所指向的类型(例如cat)和引用变量调用的方法(eat, run)，只有在运行期间才能确定是哪个类的实例对象或者方法（继承，接口）

```java
public class Animal {
    int age = 10;
    static int num = 20;
    public void eat() { System.out.println("animal eat"); }
    public static void run() { System.out.println("animal run"); }
}

public class Cat extends Animal{
    int age = 80;
    static int num = 90;
    public void eat() { System.out.println("cat eat"); }
    public static void run() { System.out.println("cat run"); }
    public void sleep() {System.out.println("cat sleep");}
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.eat(); // cat eat
        cat.run(); // animal run
        // cat.sleep(); 无法调用
        System.out.println(cat.age); // 10
        System.out.println(cat.num); // 20
    }
}
```

> 成员变量：编译看左边(Animal)，运行看左边
>
> 成员方法：编译看左边，运行看右边——动态绑定
>
> 静态方法：编译看左边，运行看左边
>
> 弊端：无法使用子类特有的属性和方法，例如sleep


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

# 版本特性
