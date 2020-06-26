# 一、并发基础

## 1. 进程和线程

- 进程：系统运行程序的基本单位（区别于操作系统）

> 系统运行一个程序，比如QQ，就是进程的创建到消亡的过程
>
> Java中，启动main函数就是创建了JVM进程，main函数所在的线程就是一个线程

- 线程：比进程更小的执行单位，一个进程有多个线程。线程共享进程资源，同时自己也拥有一部分资源

## 2. 并发和并行

## 3. 为什么使用多线程

- 计算机硬件——多核心
- 网络环境——需求多线程

## 4. 线程状态

指的是JVM中的线程状态

### 1. 新建（NEW）

创建了，但是没启动

### 2. 可运行（RUNABLE）

JVM中，开始运行。实际上，代表可以被运行，是否运行还要看资源调度。

### 3. 阻塞（BLOCKED）

请求获取对象锁，但是对象锁被其他线程占用，则处于阻塞状态。当持有锁时，变为可运行状态。

### 4. 死亡（TERMINATED）

线程结束任务，或者发生异常

### 5. 无限期等待（WAITING）

其他线程不唤醒它，则持续沉睡。醒后根据是否能抢到对象锁（CPU资源）转化为可运行或者阻塞状态。

| 进入方法                                   | 退出方法                             |
| ------------------------------------------ | ------------------------------------ |
| 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
| 没有设置 Timeout 参数的 Thread.join() 方法 | 被调用的线程执行完毕                 |


### 6. 限期等待（TIMED_WAITING）

一定时间后自动唤醒，同样在醒后变为可运行或者阻塞状态

| 进入方法                                 | 退出方法                                        |
| ---------------------------------------- | ----------------------------------------------- |
| Thread.sleep() 方法                      | 时间结束                                        |
| 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll() |
| 设置了 Timeout 参数的 Thread.join() 方法 | 时间结束 / 被调用的线程执行完毕                 |

### 等待和阻塞的区别

- 阻塞是被动的，等到有CPU资源则会变为可运行状态
- 等待是主动的，等到有CPU资源也不会转化为可运行状态

## 5. 上下文切换

- 线程阻塞时（cpu时间片耗尽），保存此时的**状态**
- 等到下次分配到CPU时间片，恢复可运行状态，运行时加载之前的**状态**，就是上下文切换

## 6. sleep和wait的区别

- sleep没有释放锁，wait释放锁
- wait需要唤醒

## 7. start和run方法

- 调用start方法后，**启动线程**，线程进入**就绪**状态，之后执行一些准备工作，最后自动调用run方法
- run方法仅仅是一个普通方法，调用后，线程在main函数中执行，不是多线程程序。

# 二、线程池

## 1. 为什么要使用线程池

- 降低资源消耗——不需要用一个创建一个，然后用完就销毁
- 提高响应速度——不需要自己创建，拿来就用
- 方便线程管理

## 2. 使用Callable和Runnable的区别

- Runnable没有返回值，或者抛出检查异常， Callable可以

## 3. 线程池的execute和submit的区别

- 都是执行线程，execute用于执行没有返回值的，submit用于执行有返回值的
- 返回`Future`对象用于判断是否执行成功，通过get方法获取值，get会阻塞当前线程直到任务完成

## 4. 创建线程池

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

#### 4.1 线程池工作流程

- 不断分配线程，直到核心线程分配完毕
- 新来的任务放进缓冲队列
- 队列满了，核心线程全部占用，还有新任务进入，则创建非核心线程，直至上限
- 如果一个线程无事可做超过keepAliveTime，而且当前线程数大于核心线程数，就停止该线程，直至线程池中的线程数目 == 核心线程数
- 如果队列满，线程数到最大线程数，还有线程来，则执行**拒绝策略**

### 4.2 拒绝策略

- AbortPolicy: 直接抛出 RejectedExecutionException 异常，是默认的拒绝策略。
- DiscardPolicy: 直接丢弃任务，不予处理也不抛出异常。如果允许任务丢失，是最好的处理策略。
- DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入队列尝试再次提交。
- CallerRunsPolicy: 使用维护线程池的线程运行，延迟较高，创建**可伸缩队列**，可以确保所有任务都执行，就是要等。

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

## 2. 线程池的使用

### 实现Callable接口

## 线程池的分类及使用

## 基本操作

## 中断

## 互斥同步

主要有两种方式实现对共享资源的互斥访问：

- JVM实现的synchronized
- JDK实现的ReentrantLock

### synchronized

#### 原理

会有一个锁对象，一个线程访问synchronized关键字修饰的方法，代码块时，会检查有没有锁，如果有则拿走进行访问，访问结束后归还，如果没有则无法访问

#### **1. 同步一个代码块**

```java
public void func() {
    synchronized (this) { // 锁对象就是这个类的实例
        // ...
    }
}
```

只能对同一个对象进行同步， 比如下面的情况

```java
public static void main(String[] args) {
    SynchronizedExample e1 = new SynchronizedExample();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> e1.func1());
    executorService.execute(() -> e1.func1());
}
```

#### **2. 同步一个方法**

```java
public synchronized void func () { // 锁仍旧是this
    // ...
}
```

#### **3. 同步一个类**

```java
public class SynchronizedExample {

    public void func2() {
        synchronized (SynchronizedExample.class) { // 锁变为class对象，一个类只有一个class对象
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
}
```

不同对象之间也可以同步

#### **4. 同步一个静态方法**

```java
public synchronized static void fun() { // 锁也是class对象
    // ...
}
```

### ReentrantLock

```java
public class LockExample {

    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock(); // 加锁
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁。
        }
    }
}

public static void main(String[] args) {
    LockExample lockExample = new LockExample();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> lockExample.func());
    executorService.execute(() -> lockExample.func());
}
```

### 二者异同

#### **1. 锁的实现**

synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的

#### **2. 性能**

新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 性能大致相同

#### 3.等待可中断

当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。

ReentrantLock 可中断，而 synchronized 不行。

#### **4. 公平锁**

公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。

synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的，传入参数true。

#### **5. 锁绑定多个条件**

一个 ReentrantLock 可以同时绑定多个 Condition 对象。

#### 总结

除非要使用ReentrantLock的高级功能，否则优先使用synchronized，因为JVM原生支持，而ReentrantLock不是所有版本的JDK都支持。并且JVM会确保锁释放，不会存在没释放的情况，ReentrantLock需要手动释放，可能造成死锁。

## 线程协作（控制执行顺序和过程）

### join()

顾名思义，加入，也就是插入，插队执行的意思

在A线程中调用B.join()，则A挂起，等待B执行完后才继续执行

### wait() notify() notifyAll()

- 对一个锁对象调用wait方法（如果是方法同步等，直接调用wait()）,则将当前线程挂起（释放锁）
- 直到被notify(), notifyAll()唤醒，或者经过一定时间（需要wait传入时间参数）后自动唤醒
- notify 是随机唤醒一个挂起线程

```java
public class WaitNotifyExample {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }
}

public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    WaitNotifyExample example = new WaitNotifyExample();
    executorService.execute(() -> example.after());
    executorService.execute(() -> example.before());
}

before
after
```

#### **wait() 和 sleep() 的区别**

- wait是Object的方法，sleep是Thread的静态方法
- wait会释放锁，sleep不会释放锁

### await() signal() signalAll()

- await类似于wait，但是可以指定等待的条件 使用Condition对象调用
- lock可以绑定多个Condition 很灵活
- signal signalAll和 notify notifyAll作用类似

```java
public class AwaitSignalExample {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    AwaitSignalExample example = new AwaitSignalExample();
    executorService.execute(() -> example.after());
    executorService.execute(() -> example.before());
}

before
after
```



- 

# J.U.S —— JDK实现的锁

java.util.concurrent

## 工具类

主要作用是更好的使用Java并发

### CountDownLatch

维护一个计数器，计数器为0时，唤醒因await方法而阻塞的线程

```java
public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }
}
// 最后输出end
// 如果没有CountDownLatch，end可能会在中间输出
```

### CyclicBarrier

当指定线程数目到达CyclicBarrier后，线程才会通过屏障的await，可以使用reset重复使用

```java
public class CyclicBarrierExample {

    public static void main(String[] args) {
        final int totalThread = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("before..");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print("after..");
            });
        }
        executorService.shutdown();
    }
}
```

### Semaphore

多个资源互斥使用，只有资源个数大于0时才可以进行访问

```java
public class SemaphoreExample {

    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount); // 设置资源数
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire(); // 请求资源
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放资源
                }
            });
        }
        executorService.shutdown();
    }
}
```



## 其他组件

### BlockingQueue接口——阻塞队列

实现类：

- **FIFO 队列** ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
- **优先级队列** ：PriorityBlockingQueue

作用：

- 当队列为空，get会阻塞
- 当队列满时，put会阻塞  
- 不再需要我们手动控制阻塞和唤醒，会自动阻塞和唤醒，兼顾效率和安全

```java
public class ProducerConsumer {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5); // 指明大小

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("produce..");
        }
    }

    private static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("consume..");
        }
    }
}

public static void main(String[] args) {
    for (int i = 0; i < 2; i++) { // 最开始只有两个生产者
        Producer producer = new Producer();
        producer.start();
    }
    for (int i = 0; i < 5; i++) { // 5个消费者，消费两次后自动阻塞
        Consumer consumer = new Consumer();
        consumer.start();
    }
    for (int i = 0; i < 3; i++) { // 传入新的生产者，继续进行消费
        Producer producer = new Producer();
        producer.start();
    }
}


```



# JMM

java内存模型，目的是为了屏蔽各种操作系统内存使用的差异。

## 工作流程

- JVM会为每个线程创建一个工作内存（栈帧），所有变量存储在主内存
- 线程加锁前，将所有相关变量拷贝进工作内存
- 线程执行过程中，只和工作内存交换信息
- 线程解锁前，必须将工作内存中的信息写回主内存
- read：把一个变量的值从主内存传到工作内存。
- load: read之后，将read得到的值放入工作内存的变量副本中。
- use：将工作内存中一个变量传递给Thread
- assign：将一个Thread中的值赋值给工作内存
- store：将工作内存中的一个变量传递给主内存
- write：在store之后，将store得到的值写回主内存
- lock与unlock

## JMM三大特性

### 1. 原子性

- 操作不可分割，保证上述8个操作具有原子性。
- 对于没有用volatile修饰的64位数据（double long）可以分为两次读写，也就是 read load store write可以不具备原子性
- 实现原子性的方式： 原子类（AtomicInteger），synchronized 

### 2. 可见性

- 当一个线程修改了共享变量的值，其他线程应该立刻得知。
- java通过更新变量后，写回内存，读取变量前从内存读取新的值来实现
- 三种方式：volatile，synchronized，final