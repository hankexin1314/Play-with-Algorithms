# 背景知识

## 一、 进程和线程

### 1. 进程和线程的异同

- **进程**：系统运行程序的基本单位（区别于操作系统）

> 系统运行一个程序，比如QQ，就是进程的创建到消亡的过程
>
> Java中，启动main函数就是创建了JVM进程，main函数所在的线程就是一个线程

- **线程**：比进程更小的执行单位，一个进程有多个线程。线程共享进程资源，同时自己也拥有一部分资源

> 同一个进程的线程共享堆和方法区，还有自己的程序计数器，虚拟栈，本地方法栈
>
> 程序计数器私有是为了：线程间的切换，回来还可以继续执行
>
> 栈私有是为了：避免局部变量不被其他线程使用

### 2. 为什么使用多线程

- 计算机底层——线程调度开销小，多核心CPU支持多线程
- 网络环境——需求多线程，多人同时访问

### 3. 线程状态

指的是JVM中的线程状态

- **新建**：创建了，但是没启动
- **可运行**：完成了资源准备，只要一有**CPU时间片**就可以运行
- **阻塞**：请求**对象锁**失败，处于阻塞，持有锁时，变为可运行
- **死亡**：线程结束，或者异常退出
- **无限期等待**：等待其他线程显式唤醒。

| 进入方法                                   | 退出方法                             |
| ------------------------------------------ | ------------------------------------ |
| 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
| 没有设置 Timeout 参数的 Thread.join() 方法 | 被调用的线程执行完毕                 |

- **限期等待**：一定时间后自动唤醒

| 进入方法                                 | 退出方法                                        |
| ---------------------------------------- | ----------------------------------------------- |
| Thread.sleep() 方法                      | 时间结束                                        |
| 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll() |
| 设置了 Timeout 参数的 Thread.join() 方法 | 时间结束 / 被调用的线程执行完毕                 |

#### 3.1 等待和阻塞的区别

- 阻塞是**被动**的，等到获得**对象锁**则会变为可运行状态
- 等待是**主动**的

#### 3.2 sleep和wait的区别

- sleep**没有释放锁**
- wait**释放锁**，wait需要**唤醒**（自动唤醒或者其他线程唤醒）

### 4. 上下文切换

- 执行该线程的**CPU时间片**耗尽时，需要保存此时的状态，等到下次分配到**时间片**，需要恢复之前这个任务的状态
- **任务从保存状态再到加载的过程**，就是一次**上下文切换**


### 5. start和run方法

- new一个Thread，线程进入了**新建状态**
- 调用start方法，会启动一个线程，使其进入**可运行**状态，等到分配到CPU时间片，就**执行**（start中会调用run）
- run方法仅仅是一个普通方法，调用后，线程在main函数中执行一个方法，不是多线程程序。

## 二、 并发

### 1. 并发和并行区别

- 并发：同一**时间段**，多个任务都在执行（不是同时）
- 并行：同一**时间点**，多个任务**同时**执行

### 2. 带来的问题

线程不安全，死锁

### 3 死锁

- 见操作系统

## 三、乐观锁和悲观锁

- 首先这两个不是特定的锁，仅仅是两种思想
- **乐观锁**：假设每次别人使用共享数据都不会修改，**不会上锁**。但是如果要更新数据，会在更新前检查：在**读取到更新这个过程中别人有没有修改**。如果修改过，再次尝试，**重新读取**，直到成功。——CAS
- **悲观锁**：假设别人每次都会修改数据，所以每次都上锁。
- 乐观锁回滚重试，悲观锁阻塞事务

# 线程安全

## 一、线程不安全的例子

```java
public class ThreadUnsafeExample {
    private int cnt = 0;
    public void add() { cnt++; }
    public int get() { return cnt; }
}

public static void main(String[] args) throws InterruptedException {
    final int threadSize = 1000;
    ThreadUnsafeExample example = new ThreadUnsafeExample();
    final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < threadSize; i++) {
        executorService.execute(() -> {
            example.add();
            countDownLatch.countDown();
        });
    }
    countDownLatch.await();
    executorService.shutdown();
    System.out.println(example.get());
}
// 997
```

- 将线程使用变量简化为三个步骤：load，assign，store，将变量从内存加载到工作内存，将变量值赋给工作内存中的变量，将工作内存中的变量写回内存

- 这三个步骤分别具有原子性，可能一个线程store前，其他线程load了旧值。

## 二、实现线程安全的方法

- **不可变**：不可变对象一定是线程安全的

>final修饰的，例如String

- **互斥同步**：在**临界资源**上加锁，要访问**临界资源**，首先要获取**锁对象**

> JVM的synchronized，volatile
>
> JDK的ReentrantLock

- **非阻塞同步**：不加锁，如果出错再采取**补救措施**——乐观锁思想

> CAS

- 无同步方案：只要**不共享数据**就不会有线程安全问题

> **栈封闭**：例如线程的虚拟栈，每个线程独享，互不干扰，局部变量不存在线程安全问题
>
> ThreadLocal
>
> **可重入代码**：可在任意时刻中断，也不影响结果的正确性

### 1. synchronized

#### 1.1 简介

- 解决多线程之间访问资源的**同步性**，该关键字可以保证被它修饰的方法或代码块**任意时刻**只能有**一个线程**执行
- JDK1.6之前，synchronized关键字直接上**重量级锁**（monitor 监视器锁），效率低下，JDK1.6之后加入了大量优化，现在的效率优化的较好。

> **monitor锁**依赖于操作系统的**Mutex Lock**(互斥锁)实现，每次加锁解锁都需要操作系统帮忙完成，需要在**用户态**和**内核态**反复切换，效率低下，时间成本高
>
> - 上锁指的是，每个对象头都会关联一个**monitor锁对象**，每个线程都会尝试获取这个对象，只有得到这个对象的线程才可以执行后续操作。

#### 1.2 使用

- **修饰实例方法**：在当前实例上加锁，进入同步代码前需要获得当前对象的**实例锁**
- **修饰静态方法**：相当于在类上加锁，作用于该类的所有实例

> 允许以下情况：A调用synchronized修饰的静态方法，B调用synchronized修饰的非静态方法
>
> 一个占用**类的锁**，一个占用当前实例**对象的锁**

- **修饰代码块**：指定加锁对象，对给定对象加锁

> 代码块和静态方法都是给类上锁，实例方法是给对象实例上锁
>
> 避免使用synchronized(String a) 因为字符串常量池具有缓存功能

#### 1.3 实现单例模式

```java
class Singleton {
    
    private volatile static Singleton uniqueInstance;
    private Singleton(){}
    public static getSingleton() {
        if(uniqueInstance == null) {
            synchronized(Singleton.class) { // 对类加锁
                if(uniqueInstance == null)
                    uniqueInstance = new Singleton();
            }
        }
        return uniqueInstance;
    }
}
```

- 为什么对类加锁后还需要判断：可能加锁前其他线程创建了一个对象，所以加锁后再进行一次判断
- volatile的必要性

>`uniqueInstance = new Singleton();`其实分三步：
>
>- 分配内存
>- 初始化实例
>- uniqueInstance指向内存
>
>因为编译器和处理器可能指令重排，可能执行顺序是1,3,2。
>
>T1执行了1,3，T2调用`getSingleton()`，发现uniqueInstance不为null，直接返回，但是uniqueInstance此时还未初始化
>
>volatile加上内存屏障，避免指令重排，保证线程安全

#### 1.4 底层原理

##### 修饰代码块

- synchronized在JVM中使用的是`monitorenter`（指向同步代码块开始的位置）和`monitorexit`（指向同步代码结束的位置）指令，`monitorenter`试图获取**monitor对象**的持有权

> 每个Java对象的**对象头**都会关联一个**monitor对象**，所以Java中任意对象可以作为锁的原因

- **锁对象**计数器为0时可以获取，获取后计数器为1，执行`monitorexit`指令后，计数器重新置为0，获取失败则阻塞等待

##### 修饰方法

- JVM是在方法上设置了一个标识，标明这是个同步方法，底层依旧是争夺`monitor对象`

#### 1.5 JDK1.6之后的锁优化

- JDK1.6之前，使用**synchronized**关键字直接加上**重量级锁**（monitor 监视器锁）
- JDK1.6之后，使用synchronized会先由**偏向锁** -> **轻量级锁** -> **重量级锁**逐步升级（锁膨胀）

> 锁升级的过程对应逐步升级的三种情况：**只有一个**线程进入临界区，多个线程**依次**进入临界区和多个线程**同时**进入临界区

##### 1)  偏向锁

- 偏向锁只允许**同一个**线程进入临界区

- 会在第一个获得锁对象的线程A上加**偏向锁**，该线程A执行完毕代码块**不会主动释放偏向锁**，下次如果是**相同线程**尝试获取锁对象，则直接执行，否则锁升级为**轻量级锁**

> - 第一个线程获得锁对象，将锁置为**偏向状态**，然后在Mark Word（位于对象头中）中记录第一个执行该代码块的**线程id**（整个过程都是CAS操作），下次执行时首先检查线程id
> - JDK1.6偏向锁默认开启，之前默认关闭

##### 2)  轻量级锁

- **偏向锁**关闭，或者多个线程**竞争偏向锁**则升级为**轻量级锁**。轻量级锁允许不同线程**依次**进入临界区。

>- 在当前线程栈帧中创建一个**锁记录**(Lock Record)，用来存储Mark Word的拷贝，然后将Lock Record中的owner指向当前对象
>- JVM尝试用CAS把对象原本的Mark Word更新为Lock Record的指针，更新成功则说明获取锁成功
>- 如果失败，检查Mark Word是否指向当前线程的Lock Record，如果是则说明该线程持有该对象锁。否则说明锁被其他线程持有，继续锁升级。

##### 3) 自旋锁和自适应自旋锁

- 竞争轻量级锁的线程会进入自旋状态（**忙等**）

- 线程阻塞十分消耗资源。线程的阻塞和唤醒都需要进入**内核态**完成，用户态到内核态很耗时间（**其实使用忙等时间换取用户态和内核态之间切换的开销**）
- 很多应用中，共享数据只会锁定很短一段时间。如果每次遇到共享资源被锁定就阻塞很不值
- 所以自旋锁允许线程自己循环一段时间，避免进入阻塞状态。

> JDK1.6之前就有，只是默认关闭，JDK1.6之后默认开启，且引入了自适应的自旋锁

- 自旋会占用CPU资源，所以不能无限自旋。默认**自旋10次**还未得到资源就阻塞，可以手动修改。
- **自适应自旋锁**，自旋时间不固定，根据上一个自旋的线程的自旋时间和占用锁的线程的状态来一起决定，很智能

##### 4) 锁消除

- JVM检测到不可能存在竞争的共享资源上有锁，则会消除它，减少资源消耗

> 例如字符串相加 `s1 + s2 + s3`
>
> 其实JVM会将其解析为一个StringBuffer不断地append，StringBuffer是线程安全的，所以每次append都需要加锁释放锁
>
> JVM会检测到在这里加锁没必要，所以就会消除

##### 5) 锁粗化

- JVM检测到重复对同一个对象加锁释放锁，就会将锁的时长增大，减少加锁解锁次数

> 还是上一个例子，JVM会在第一次append上加锁，最后一次append之后解锁，消除掉中间的锁

#### 1.6 synchronized和ReentrantLock 的区别

- 二者都是**可重入锁**，性能大致相同

> 一个线程获取了某个对象锁，还未释放，如果它还想获得这个对象锁，是可以获得的，这就叫**可重入锁**，如果不可重入，就会造成死锁。

- synchronized依赖于JVM，ReentrantLock依赖于JDK（lock(), unlock()配合try finally）
- synchronized自动释放锁，ReentrantLock手动释放锁
- synchronized非公平锁（先来等待的不一定先获得锁），ReentrantLock默认非公平，也可以是公平的

> 非公平一般吞吐量大于公平锁

### 2. volatile

保证可见性，防止指令重排

> 告诉JVM，这个变量不稳定，每次使用直接去主存中读取

#### 2.1 Java内存模型（JMM）

- CPU寄存器速度比内存快很多，为了避免速度差异带来的问题，引入了高速缓存，多处理器系统中，每个处理器都有自己的高速缓存。
- 高速缓存带来了**缓存一致性**的问题，多个缓存共享一块内存，可能导致多个缓存数据不一致
- **JMM规定**：所有**共享变量**都在**主存**中，线程的**工作内存**中保存了**变量的副本**，线程只能使用自己**工作内存**中的变量，不同线程之间变量值传递必须通过内存完成

#### 2.2 内存模型三个特性

- **原子性**：一个或多个操作，要么全部执行，且中间不会受到干扰而中断，要么全不执行。可以使用**原子类**或者**synchronized**可保证原子性

- **可见性**：一个线程对共享变量修改，其他线程应该看到的是修改后的值

> 实现方式：
>
> - synchronized，更新完成前，其他线程阻塞等待
> - volatile：线程不安全的例子中使用volatile不能解决 问题，因为volatile不保证原子性
> - final

- **有序性**：java允许编译器和处理器对指令重排，所以编写顺序和执行顺序可能不同。对于单线程，没问题，对于多线程可能出错。

> volatile通过添加**内存屏障**来禁止指令重排
>
> synchronized保证每个时刻只有一个线程执行改代码，相当于变为单线程

#### 2.3 synchronized和volatile的 区别

- volatile是轻量级同步工具，只能作用于**变量**，性能好。synchronized相对重量级，可以作用于方法，代码块性能差点，但是功能多
- 多线程访问volatile修饰的变量不会阻塞，而synchronized会阻塞
- volatile只保证可见性，不保证原子性，synchronized都保证
- volatile主要还是解决多线程中变量可见性问题，synchronized主要解决同步问题

### 3. CAS

- Compare-and-Swap，比较与交换。硬件支持的原子操作。有三个参数，内存地址V，旧的预期值A，新值B，只有内存中的值等于A，才会将其更新为B

#### 1. 一个例子

- AtomicInteger的`incrementAndGet`方法
- 不断重试，直到成功

```java
private AtomicInteger cnt = new AtomicInteger();

public void add() {
    cnt.incrementAndGet();
}

public final int incrementAndGet() {
    return unsafe.getAndAddInt(this, valueOffset, 1) + 1; // 调用这个 this就是内存地址 offset是偏移量，1为要增加的值
}

public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2); // 重新读取，获取旧的预期值
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4)); // CAS操作，如果var1 + var2的地址上的值与var5相同，则将其更新为var5 + var4

    return var5; // 返回的是旧值
}
```

#### 2. ABA

- 如果一个变量值从A变为B，最后又变回A，CAS会认为它没改变过，
- 解决这种问题JUC包引入了一个变量值的版本，其实这个问题不影响程序正确性
- 使用互斥同步解决这个问题更好

### 4. ThreadLocal

- 使用方式类似于一个Map，里面存着键值对。
- 不同的是，Map是所有线程都共享一份键值对，它是每个线程独享一份键值对的副本，且线程只能操作自己的键值对

#### 4.1 使用

- 数据库连接池，从中取链接的时候

#### 4.2 原理

- 每个线程中都有个`ThreadLocalMap`对象，该对象默认为null，可以理解为一个HashMap

> 其实不是哈希map，是数组，index为利用ThreadLocal对象计算出来的哈希值

- 当线程调用`ThreadLocal`的get，set方法时，才会创建这个对象，其实我们调用的是`ThreadLocalMap`的get，set方法
- 其实变量是放在线程自己的Map中的，ThreadLocal只是一个封装
- 每个线程都有自己的`ThreadLocalMap`，`ThreadLocalMap`存放以`ThreadLocal`为key，Object为value的键值对（不同的数据使用不同的ThreadLocal对象存储）

#### 4.3 内存泄漏

- `ThreadLocalMap`中的key和`ThreadLocal`为弱引用（下次GC就被回收）,value为强引用。
- 所以很有可能出现key为null的value，永远无法被回收，所以需要手动调用`remove()`

# 线程池

## 一、 为什么要使用线程池

- 降低资源消耗——不需要用一个创建一个，然后用完就销毁
- 提高响应速度——不需要自己创建，拿来就用
- 方便线程管理

## 二、 使用Callable和Runnable的区别

- Runnable没有返回值，或者抛出检查异常， Callable可以

## 三、 线程池的execute和submit的区别

- 都是执行线程，execute用于执行没有返回值的，submit用于执行有返回值的
- 返回`Future`对象用于判断是否执行成功，通过get方法获取值，get会阻塞当前线程直到任务完成

## 四、创建线程池

- 使用`ThreadPoolExecutor`创建线程池，可以自定义(**set方法**设置参数)，也可以使用预设好的，spring使用`ThreadPoolTaskExecutor`创建
- FixedThreadPool：线程池中线程始终不变
- SingleThreadExecutor：只有一个线程
- CachedThreadPool：只要线程不够就创建新线程

```java
public ThreadPoolExecutor(
    int corePoolSize,//线程池常驻核心线程数
    int maximumPoolSize,//线程池能容纳同时执行最大线程数
    long keepAliveTime,//多余的空闲线程的存活时间
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,//被提交尚未被执行的任务队列
    ThreadFactory threadFactory,//创建线程的线程工厂
    RejectedExecutionHandler handler//拒绝策略
    ) 
{...}
```

### 4.1 线程池工作流程

- 不断分配线程，直到核心线程分配完毕
- 新来的任务放进缓冲队列
- 队列满了，核心线程全部占用，还有新任务进入，则创建非核心线程，直至上限
- 如果一个线程无事可做超过keepAliveTime，而且当前线程数大于核心线程数，就停止该线程，直至线程池中的线程数目 == 核心线程数
- 如果队列满，线程数到最大线程数，还有线程来，则执行**拒绝策略**

### 4.2 拒绝策略

- AbortPolicy: 直接抛出 RejectedExecutionException 异常，是**默认**的拒绝策略。
- DiscardPolicy: 直接丢弃任务，不予处理也不抛出异常。如果允许任务丢失，是最好的处理策略。
- DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入队列尝试再次提交。
- CallerRunsPolicy: 使用维护线程池的线程运行，延迟较高，创建**可伸缩队列**，可以确保所有任务都执行，就是要等。

# 未整理笔记

### AQS

- AbstractQueuedSynchronizer 抽象队列同步器，是一个用来构建锁和同步器的框架，例如ReentrantLock和Semaphore都是它构建的。

#### 1. 原理

- **核心思想**：如果一个线程请求的共享资源空闲，则该线程是有效的工作线程，并且将共享资源加锁。如果请求的共享资源被占用，则需要一系列线程阻塞等待和唤醒时锁分配机制。
- 这个机制AQS通过CLH队列（虚拟的双向队列）实现，将暂时获取不到锁的线程放入队列中。
- AQS通过int类型来代表资源，对于ReentrantLock(临界区只能一个线程进入)，int = 0代表资源空闲，int = 1 代表资源占用。而对于Semaphore，int可初始化为
- 通过FIFO对线程排序，通过CAS操作对共享资源更新

```java
private volatile int state;//共享变量int，使用volatile修饰保证线程可见性

protected final int getState() {  
        return state;
}
 // 设置同步状态的值
protected final void setState(int newState) { 
        state = newState;
}
//原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```

#### 2. 资源共享方式

- 独占，只有一个线程可以占用共享资源，例如ReentrantLock
- - 公平锁：按照队列顺序，先到先得
  - 非公平锁：谁抢到是谁的
- 可共享，可有多个线程同时占用共享资源，例如Semaphore(信号量)

#### 3. AQS组件总结

- **CountDownLatch （倒计时器）：** 同步工具类，倒计时器。每次调用`countDown()`会使计时器减一，到0时，调用await()的方法等待的线程会被唤醒

```java
public static void main(String[] args) throws InterruptedException {
    final int totalThread = 10;
    CountDownLatch countDownLatch = new CountDownLatch(totalThread); // 倒计时10次
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < totalThread; i++) {
        executorService.execute(() -> {
            System.out.print("run..");
            countDownLatch.countDown();
        });
    }
    countDownLatch.await();
    System.out.println("end"); // 输出10个run后再输出end
    executorService.shutdown();
}
```

- **CyclicBarrier(循环栅栏)：**作用类似，也是计数器，每次调用`await()`方法计数器减一，减到0时，唤醒所有await的线程

```java
public CyclicBarrier(int parties, Runnable barrierAction) { // 构造函数  第一个参数是要拦住多少个线程，第二个参数是计数器为0时要执行的操作
    if (parties <= 0) throw new IllegalArgumentException();
    this.parties = parties;
    this.count = parties;
    this.barrierCommand = barrierAction;
}

public CyclicBarrier(int parties) {
    this(parties, null);
}
```

```java
public static void main(String[] args) {
    final int totalThread = 10;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < totalThread; i++) {
        executorService.execute(() -> {
            System.out.print("before..");
            try {
                cyclicBarrier.await();  // 拦住线程
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.print("after.."); // 先输出10个before，然后才有after输出
        });
    }
    executorService.shutdown();
}
```

### ReentrantLock

```java
private Lock lock = new ReentrantLock();
public void func() {
    lock.lock();
    try {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    } finally {
        lock.unlock(); // 确保释放锁，从而避免发生死锁。
    }
}
```

- 





- 

- 

# 二、基本用法

## 1. 线程的使用

创建一个线程的方法：

- 继承Thread类
- 实现Runnable接口（重写run方法）
- 实现Callable结构（重写call方法）

```java
public class MyThread extends Thread {
    public void run() {
        // ...
    }
}
public static void main(String[] args) {
    MyThread mt = new MyThread();
    mt.start();
}
// ---------------------------------------------------------
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // ...
    }
}
public static void main(String[] args) {
    MyRunnable instance = new MyRunnable();
    Thread thread = new Thread(instance);
    thread.start();
}
// ------------------------------------------------------------
public class MyCallable implements Callable<Integer> {
    public Integer call() {
        return 123;
    }
}
public static void main(String[] args) throws ExecutionException, InterruptedException {
    MyCallable mc = new MyCallable();
    FutureTask<Integer> ft = new FutureTask<>(mc);
    Thread thread = new Thread(ft);
    thread.start();
    System.out.println(ft.get());
}
```

### 使用接口还是继承

- Java单继承，所以用接口
- 接口开销小，继承类开销较大


