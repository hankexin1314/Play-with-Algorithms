# 概述

JVM 包含两大部分内容 ： **内存管理**和**类加载机制**

- 因为Java自动管理内存，所以我们要弄明白JVM是怎么管理内存的，这里就包含两部分，一个是**内存如何分配**，一个是**内存如何回收**（垃圾回收）
- Java是纯面向对象语言，万物皆类，我们想要弄明白，类（也就是我们写的代码）是如何从代码，到加载到JVM，再到运行的，也就是**类加载机制**。

## 易混淆概念的介绍

### 1. 符号引用，直接引用，动态链接，静态解析

- **符号引用**：指的是用符号（比如字符串）来描述所引用的目标，形式不限，只要可以指明即可。

> java/io/PrintStream.println:(Ljava/lang/String;)V，println的符号引用
>
> 这个字符串存放在**class文件常量池**中，调用方法就是拿这个字符串做参数

- **直接引用**：使用JVM能懂的方式来描述所引用的目标（指针，可定位到目标的句柄，相对偏移量等）
- **静态解析**：在类加载的解析阶段，将符号引用转化为直接引用
- **动态链接**：在运行时，将符号引用转化为直接引用（运行后解析）

> 为什么不在解析时全部转化？
>
> 过于耗时，链接的类太多

### 2. 常量池

有三种常量池：

- **全局字符串池**：**准备**阶段之后，将**堆**中的字符串实例的**引用**存放到池中

> 这个池一直存在，池中是引用，实例对象在堆中，一个JVM只有一个全局池，所有类共享

- **class文件常量池**：`.class`文件中包括：版本，接口，方法还有常量池等信息，存放：字面量和符号引用。

> 字面量：文本字符串，被声明为final的常量

- **运行时常量池**：`.class`文件加载到内存中后，就会将**class文件常量池**复制到**运行时常量池**，每个类都有一个运行时常量池。

#### 一个具体的例子

```java
public class HelloWorld {
    public static void main(String []args) {
		String str1 = "abc"; 
		String str2 = new String("def"); 
		String str3 = "abc"; 
		String str4 = str2.intern(); 
		String str5 = "def"; 
		System.out.println(str1 == str3);//true 
		System.out.println(str2 == str4);//false 
		System.out.println(str4 == str5);//true
    }
```

- String是final的，在编译阶段，str1的`abc`放入堆，引用放入**全局字符串常量池**
- 解析str3的时候，会去**全局字符串常量池**中查找，找到了，所以 ==
- 对str2，会创建两个实例：一个是字面量存放在堆中的，引用存放在**全局字符串常量池**的实例；另一个是存放在堆中的，new出来的实例，str2是后者的指针
- str4使用intern，返回**全局池**中的`def`的引用，如果没有，则将引用添加进去，因为有所以直接返回引用，但是这个引用和str2的引用是不一样的，属于前者
- str5直接从**全局池**中拿出引用

# 一、内存管理

- 首先我们要明白基础知识，我们的代码 `.java`文件，先经过编译器编译，变成`.class`文件，也就是**java字节码**，然后字节码从硬盘进入JVM进行执行
- 内存管理包括**内存区域**的划分，**内存分配**和**垃圾回收**

## 内存区域

内存是怎么分配的，我们要管理的是哪些部分，主要分为三部分：

### 1. 线程私有区域

- **程序计数器**：指示要执行的**字节码**指令的行号指示器，通过改变这个数值，实现分支，循环，跳转之类的操作。

> 为了线程切换后可以恢复到之前的状态，所以每个线程都必须维护一个

- **虚拟机栈**：栈中的元素是**栈帧**，一个方法的调用对应着栈帧从压入到弹出。每个栈帧中，维持着这个方法的**局部变量表**，**对象引用**（可能是地址或句柄等）等信息。

> 栈有容量上限，如果不允许自动扩展：**StackOverFlowError**
>
> 如果可动态扩展，扩展到内存耗尽：**OutOfMemoryError**

- **本地方法栈**：和虚拟机栈类似，虚拟机栈服务的是**Java方法**（字节码），本地方法服务的是本地方法。

> 本地方法一般指用其他语言（例如C，C++）编写，并且被编译为基于本机硬件和操作系统的程序

### 2. 线程共享区域

- **堆**：存放对象的实例，垃圾回收的主要区域

> 可动态扩展：**OutOfMemoryError**

- **方法区**：存放**已经加载的类的信息**，**常量**，**静态变量**等数据。JDK8以前，方法区就是永久代（不进行垃圾回收），很容易内存溢出，因为方法区有大小上限。JDK8，废弃永久代概念，将**常量**和**静态变量**放入堆，将其他信息放入**元空间**（直接内存）

### 3. 直接内存

- **直接内存**：不属于JVM数据区域，JDK1.4引入NIO类，基于**通道**和**缓冲**，可以直接读写直接内存，避免了在JVM和直接内存中反复复制数据。

>可动态扩展：**OutOfMemoryError**

## 垃圾回收

垃圾回收主要针对**堆**和**方法区**进行

### 1. 一些概念

- 新生代(Young Generation)：堆划分的区域
- 老年代(Old Generation)：堆划分的区域
- 新生代收集(Minor GC)：针对新生代的垃圾收集
- 老年代收集(Major GC):只针对老年代的垃圾收集
- 整堆收集(Full GC)：收集整个堆和方法区

### 2. 内存分配和回收策略

#### 内存分配策略

- **对象优先在Eden分配**，Eden不够时Minor GC
- **大对象直接进入老年代**，避免Eden和Survivor之间的大量内存复制，可手动设置阈值
- **长期存活的对象进入老年代**，设置计数器记录年龄，设置threshold定义年龄阈值
- **动态对象年龄判断**，并不是一定要到阈值才进入老年代，如果Survivor有一半以上的空间存着年龄`n`的对象，则年龄大于`n`的对象进入老年代
- **空间分配担保**，Minor GC前，检查老年代**最大可用连续空间**，是否大于新生代所有对象总空间，如果大于，则说明这次Minor GC安全（可能新生代中的对象全部晋升到老年代）。如果不大于，检查是否允许担保失败，如果允许，则检查老年代**最大可用连续空间**是否大于**新生代平均晋升老年代所需空间**，如果大于，则进行Minor GC，否则Full GC

#### 内存回收策略（Full GC触发条件）

Eden空间满就Minor GC

- **调用System.gc()** ，建议JVM垃圾回收，不一定真回收。不建议使用，建议交给JVM回收
- **老年代空间不足**，少创建大对象和数组（因为大对象直接进入老年代），调大新生代大小，增大进入老年代的年龄阈值（减少进入老年代的对象数）
- **空间担保失败**
- JDK1.7以前，方法区是永久代，**永久代空间不足**
- **Concurrent Mode Failure**

### 3. 判断对象是否已经死亡

#### 3.1 引用计数法

- 每当有一个地方引用该对象，计数器值 + 1，引用失效，计数器值 -1
- 计数器为0时表示该对象不会再被使用

> 循环引用

#### 3.2 可达性分析法

- 通过一系列称为**GC roots**的对象作为起点向下搜索，可达的就是存活的，不可达的被回收
- 类静态属性引用的对象，局部变量表中引用的对象等都可作为roots

#### 3.3 方法区的回收

- 主要回收目标是**常量池**和**类的卸载**，效率不高
- 为了避免溢出，大量使用反射和动态代理的场景需要回收
- 类卸载需要满足：所有实例都回收，对应ClassLoader回收，对应的Class对象没有在任何地方被引用，也无法通过反射访问到。

#### 3.4 finalize()

- 可达性分析后会将不可达的对象标记，GC时，如果对象没有覆盖finalize或者finalize被调用过，则直接回收。
- 如果重写了finalize且从未执行过，则执行该方法，则对象有复活机会，例如在finalize中将自己的引用赋值给static变量。只可自救一次。
- 不建议使用，代价高，不确定性大

#### 3.5 引用类型

- **强引用**：被强引用关联的对象不会被回收。

> 使用new创建对象就是强引用

- **软引用**：只有内存不足时才会被回收

>```java
>Object obj = new Object();
>SoftReference<Object> sf = new SoftReference<Object>(obj);
>obj = null;  // 使对象只被软引用关联
>```

- **弱引用**：被关联对象下一次垃圾回收**一定**被回收

>```java
>Object obj = new Object();
>WeakReference<Object> wf = new WeakReference<Object>(obj);
>obj = null;
>```

- **虚引用**：不会对关联对象生存时间产生影响，无法通过虚引用得到一个对象，关联对象被回收时会收到一个系统通知

>```java
>Object obj = new Object();
>PhantomReference<Object> pf = new PhantomReference<Object>(obj, null);
>obj = null;
>```

### 4. 垃圾收集算法

#### 4.1 标记-清除算法

- 第一遍将活动对象标记，第二遍回收未标记的对象
- 分配时，维护一个空闲链表，找到第一个大于等于新对象大小块就分配，多余部分返回给空闲链表
- 清除后会合并相邻块

> 标记和清理效率低下，反复遍历所有块
>
> 内存碎片

#### 4.2 标记-整理算法

- 清理阶段让所有存活对象移向同一端

> 没有内存碎片
>
> 需要移动大量块，效率低下

#### 4.3 复制算法

- 将内存划分为大小相等的两块，每次只用一块
- 第一块快满了，就将存活的对象复制到另一块，清除第一块

> 只使用了内存的一半
>
> 现代虚拟机对于**新生代**GC都使用这个算法，将内存分为8:1:1，一块**Eden**，两块**Survivor**，每次GC将Eden和一块Survivor上存活的对象复制到另一块Survivor上后就清除。
>
> 如果Survivor放不下了，借用**老年代**空间进行空间分配担保，也就是借用空间来存储

#### 4.4 分代收集

将堆分为**新生代**和**老年代**，采用不同的收集算法：

- 新生代：复制算法
- 老年代：标记清除或者标记整理

根据两条假说：

- 强分代理论：存活过越多轮GC的对象越难被杀死
- 弱分代理论：大部分对象，朝生夕死

推论出：

- 跨代引用假说：跨代引用相对于同代引用占极少数

> 如何解决跨代引用问题

- 在**新生代**上构建一个**记忆集**，将**老年代**分为多块，记录哪一块会存在跨代引用
- Minor GC时，只会额外扫描包含跨代引用的小块内存里的对象

### 5. 垃圾收集器

![68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f63363235626161302d646465362d343439652d393364662d6333613637663266343330662e6a7067](E:\workspace\Play-with-Algorithms\img\68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f63363235626161302d646465362d343439652d393364662d6333613637663266343330662e6a7067.jpg)

#### 5.1 Serial 收集器

- 单线程收集器，没有线程切换，高效、
- Client默认的新生代收集齐

#### 5.2 ParNew 收集器

- 多线程版Serial
- Server默认的新生代收集齐

#### 5.3 Parallel Scavenge 收集器

- 多线程收集器，目标是尽可能提高吞吐量（CPU利用率）
- 其他收集器的目标一般是：减少用户线程停顿时间
- **自适应调节策略**，可以指定**最大停顿时间**和**吞吐量**，也可以**开启收集器自动控制模式**，只需要指定**堆容量上限**和**更关注停顿时间还是吞吐量**，收集器自动根据系统状态调节新生代大小，各区比例，晋升老年代的Threshold来优化。

#### 5.4 Serial Old 收集器

- serial收集器的老年代版本，单线程

#### 5.5 Parallel Old 收集器

- Parallel Scavenge 收集器的老年代版本

#### 5.6 CMS收集器

- 多线程，使用标记-清除算法

垃圾回收流程：

- **初始标记**：标记和GC Roots直接相连的对象，需要停顿，很快
- **并发标记**：GC Roots Tracing，并发，不需要停顿，耗时最长
- **重新标记**：修正并发过程中标记变动的那一部分，需要停顿
- **并发清除**：不需要停顿，耗时很长

优点：

- 停顿时间短，并发

缺点：

- 吞吐量低
- 标记-清除算法导致内存碎片，提前触发Full GC

- 无法清理浮动垃圾（并发清除时产生的垃圾，只能到下一次GC回收），可能出现Concurrent Mode Failure（不能等到老年代快满才进行GC，必须预留空间给浮动垃圾，预留空间不足就会出现这个错误）

#### 5.7 G1收集器

- G1（Garbage-First），面向服务端，并发。
- **基本机制**：将堆分为许多大小相等的**Region**，每个Region都可根据需要，扮演老年代，Eden，或Survivor。垃圾回收的基本单位是Region而不是新生代或者老年代

特点：

- **空间整合**：整体看是**标记-整理**算法，堆每个Region是**复制**算法（将存活对象复制，然后清除整个Region）
- **可预测停顿时间**：因为Region大小相等，且每次GC只对整数个Region进行回收，所以时间可预测

如何存储大对象？

- 有专门用来存储大对象的Region，超大对象会放在连续多个Region中

如何解决跨Region引用？

- 每个Region都有记忆集(Remember Set)，用来记录该Region引用了谁和被谁引用。
- 避免了可达性分析时全盘扫描

垃圾回收流程：

- **初始标记**：标记与GC Roots直接相连的Region，停顿，很快
- **并发标记**：可达性分析，并发，不用停顿，耗时
- **最终标记**：修正并发过程中标记改变的那一部分，停顿
- **筛选回收**：并发，维护一个**优先级列表**，优先级高低的依据是回收的性价比，可根据用户所**期望的停顿时间**（可手动设置）来平衡**吞吐量**和**延迟**

# 二、类加载机制

- 类是在运行中动态加载的，不是一次性全部加载的，因为过于耗费内存。
- 类加载机制：JVM将`.class`文件加载到内存，然后经过校验，解析，初始化，将其转化为可以被JVM直接使用的java类型的过程。

![68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f33333566653139632d346137362d343561622d393332302d3838633930643661306437652e706e67](E:\workspace\Play-with-Algorithms\img\68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f33333566653139632d346137362d343561622d393332302d3838633930643661306437652e706e67.png)



- 其中，验证 + 准备 + 解析 = 连接

## 类加载过程

### 1. 加载

完成三件事情：

- 通过类的全限定名获取定义该类的二进制字节流（找到`.class`文件）
- 将该字节流表示的静态存储结构转化为**方法区**的运行时存储结构（将`.class`文件加载到JVM）

- 在内存中生成一个代表该类的`java.lang.Class`对象，作为方法区中该类各种数据访问的入口（JVM中的存储形式）

获取二进制字节流的方式：

- 从zip包读取，jar，war格式的基础
- 运行时计算生成，例如动态代理
- 由其他文件生成，JSP生成Class类

### 2. 验证

- 确保`.class`文件格式符合JVM的要求，且不会危害JVM的安全
- 一部分验证在**编译**阶段实现，如果**编译**阶段验证通过，会提供一个校验位，在**验证**阶段只需要检查这个校验位即可。
- 非不是必要阶段，如果确保`.class`文件无误，可以手动关闭这个阶段

### 3. 准备

- 为**静态变量**（static）分配内存，设置初始值。

> **静态变量**逻辑上位于**方法区**中，实际上在**堆**中分配内存
>
> 初始值：
>
> - 对于`public static int value = 123;`，value的初始值为 0，在**初始化**阶段赋值为123
> - 对于`public static final int value = 123;`，编译时生成`ConstantValue`属性，**准备**阶段赋值为123

### 4. 解析

- 将常量池（运行时常量池）中的**符号引用**替换为**直接引用**
- 为了支持动态链接，可以将**解析**放在**初始化**之后

> 解析是会查找全局字符串池的

### 5. 初始化

- 初始化阶段才真正开始执行类中定义的java代码

- 初始化阶段就是执行`<clinit>()`方法的过程

> 这个方法是将类中static修饰的代码块和赋值语句组合起来的方法
>
> 执行顺序：父类 -> 子类
>
> 对于接口，没有严格的先父类再子类，使用到才初始化

- 线程安全，多线程初始化同一个类，只有一个线程执行`<clinit>()`，其余线程阻塞

#### 需要立即初始化的六种情况

属性，方法层面

- 遇到 new、getstatic、putstatic、invokestatic 这四条**字节码指令**时，如果类没有进行过初始化，则必须先触发其初始化。

>这几条指令对应的操作：
>
>- 实例化对象
>- 读取或设置静态变量（final修饰的准备阶段准备完毕，不需要）
>- 调用静态方法

- 当使用 JDK 1.7 的动态语言支持时，如果一个 java.lang.invoke.MethodHandle 实例最后的解析结果为 REF_getStatic, REF_putStatic, REF_invokeStatic 的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化；

类，接口方面

- 使用 java.lang.reflect 包的方法对类进行反射调用的时候，如果类没有进行初始化，则需要先触发其初始化。
- 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
- 当虚拟机启动时，用户需要指定一个要执行的主类（包含 main() 方法的那个类），虚拟机会先初始化这个主类；

- 接口有default方法，如果接口的实现类初始化，则要先初始化接口

### 6. 卸载

- Class对象被垃圾回收

## 类与类加载器

- 两个类相等，需要类本身相等，并且使用同一个类加载器加载，因为每一个类载器都有独立的类名称空间
- 类加载器的功能：通过一个类的全限定名，获取该类的二进制字节流

### 1. 分类

- **启动类加载器**（Bootstrap ClassLoader）：最顶层，由C++实现，负责加载`%JAVA_HOME%/lib`目录下的jar包和类。
- **扩展类加载器**（Extension ClassLoader）：负责加载`%JRE_HOME%/lib/ext` 目录下的jar包和类，或被 `java.ext.dirs` 系统变量所指定的路径下的jar包
- **应用程序类加载器**（Application ClassLoader）：面向用户的加载器，负责加载当前应用classpath下的所有jar包和类。程序默认的类加载器。

### 2. 双亲委派模型

#### 2.1 工作过程

<img src="E:\workspace\Play-with-Algorithms\img\classloader.png" alt="classloader" style="zoom:75%;" />

- 除了顶层外，其他的类加载器都要有自己的父类加载器，组合关系，而不是继承关系。
- 首先将请求转发给父类加载器，父类加载器不能处理再自己处理

#### 2.2 优点

- 带有优先级的类加载器关系，使得基础类的类加载器得到统一（优先使用启动类加载器加载`lib`文件下的类）

> 避免了重复加载，保证核心API不被篡改
>
> 如果没有双亲委派，我们在classpath下自定义一个`Object`类，程序运行时就会出现多个Object

#### 2.3 实现

- 部分源代码，如果要破坏双亲委派模型，重写loadClass即可

```java
public abstract class ClassLoader {
    // 不是继承关系，是组合关系
    private final ClassLoader parent;

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 首先检查该类是否已经被加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                try {
                    if (parent != null) { // 用父类加载器加载
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // 如果父类加载器加载失败，用该类加载器加载
                    c = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}
```

- 自定义类加载器，重写findClass方法即可

```java
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir; // 根目录，去哪里寻找字节码文件

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name); // 读取字节码文件
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            // 将字节码文件转化为类的实例
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
```

