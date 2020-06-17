# 线程的基础用法

## 如何创建一个线程

### 实现Callable接口

和Runnable最大的区别就是有返回值

```java
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



## 线程状态

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