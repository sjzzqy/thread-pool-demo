

# 线程池	

## ThreadPoolExecutor

java.util.concurrent.  包



public **ThreadPoolExecutor**(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) 



|           参数           | 描述             |
| :----------------------: | :--------------- |
|       corePoolSize       | 核心线程数       |
|     maximumPoolSize      | 最大线程数       |
|      keepAliveTime       | 空闲线程存活时间 |
|         TimeUnit         | 时间单位         |
|      BlockingQueue       | 任务队列         |
|      ThreadFactory       | 线程工厂         |
| RejectedExecutionHandler | 任务拒绝策略     |

### 构造参数：

| corePoolSize | maximumPoolSize | keepAliveTime |     TimeUnit     |
| :----------: | :-------------: | :-----------: | :--------------: |
|      2       |        4        |      10       | TimeUnit.SECONDS |

1. corePoolSize：核心线程是2个，  核心线程只要线程池不关闭，她就不会被销毁；

2. maximumPoolSize：线程池中最大线程数，减去核心线程，其他为非核心线程，没有执行任务的话，会被清理，多久被清理取决于后两个参数

3. keepAliveTime：非核心线程 在没有工作的情况下的存活时间，10是表示10秒后被销毁；

4. TimeUnit：时间单位，TimeUnit.SECONDS 秒，MILLISECONDS 毫秒

5. BlockingQueue：任务队列，可以放任务的容器，线程池中的线程都是从这里领取任务

   ```
           LinkedBlockingQueue<>();// 链式阻塞队列；
   		ArrayBlockingQueue<>();// 数组阻塞队列
   ```

   

6. ThreadFactory ：线程工厂，指定线程如何生产，是一个接口，实现里面的newThread方法

   ```
   public interface ThreadFactory {
       /**
        * 构造一个新线程，可以指定线程名称、优先级等
        * @param r 新线程执行的任务
        * @return 构造的新线程，如果创建线程的请求被拒绝，则为null
        */
       Thread newThread(Runnable r);
   }
   ```

   ```
   /**
    * 
    * @author Qzzy
    * @Generated 2022/06/11 20:05
    */
   public class ImplementThreadFactory implements ThreadFactory {
   
   	@Override
   	public Thread newThread(Runnable r) {
   		Thread thread = new Thread();
   		thread.setName("订单线程" + i.getAndIncrement() + "号");//可以指定线程名称
   		return thread;
   	}
   }
   ```

   如果不想自定义线程工厂，可以使用Exceutors类中的默认工厂，

   ```
   Executors.defaultThreadFactory();
   
       /**
        * 默认的线程工厂
        */
       public static ThreadFactory defaultThreadFactory() {
               return new DefaultThreadFactory();
       }
   ```

   7. Handler ：任务拒绝策略，满足4个条件会被拒绝，
      - [ ]  线程池中的线程已满
      - [ ] 无法再继续扩容
      - [ ] 没有空闲线程
      - [ ] 任务队列已满，无法再存入新任务

#### 代码演示：

```
/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 20:19
 */
public class ThreadPoolTask implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		
	}

}
```

```
/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 20:05
 */
public class ImplementThreadFactory implements ThreadFactory {

	/**
	 * 线程计数器，原子，不存在线程安全问题
	 */
	private final AtomicInteger i = new AtomicInteger(1);

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread();
		thread.setName("订单线程" + i.getAndIncrement() + "号");
		return thread;
	}
}
```



```
/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 19:53
 */
public class ThreadPoolTester {

	public void test() {

		//创建任务
		ThreadPoolTask task1 = new ThreadPoolTask();
		ThreadPoolTask task2 = new ThreadPoolTask();
		ThreadPoolTask task3 = new ThreadPoolTask();

		/** 核心线程2个，最大线程4，存活时间10秒，存活时间10秒， */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS, 
				new LinkedBlockingQueue<>(), 
				new ImplementThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
	
		//提交任务
		threadPoolExecutor.execute(task1);
		threadPoolExecutor.execute(task2);
		threadPoolExecutor.execute(task3);
		
        //关闭线程池
		threadPoolExecutor.shutdown();
	}
}
```



### execute 和 submit区别

- [ ] execute 位于java.util.concurrent.Executor，只可以提交Runnable任务，Runnable是无返回值的任务；
- [ ] submit  位于java.util.concurrent.ExecutorService，可以提交Runnable任务 也可以提交Callable任务，执行结果会封装到Future对象中，然后返回给调用者；适合有返回结果的任务；

**submit 方法使用：**

| 方法名                                               | 返回值类型 | 描述                                                         |
| ---------------------------------------------------- | ---------- | ------------------------------------------------------------ |
| public Future<?> submit(Runnable task)               | Future<?>  | 作用是提交Runnable任务，返回的是Future，既然Runnable任务是无返回结果为什么还用submit呢，因为Future不光获取任务执行结果，还可以跟踪任务是否执行完毕、以及取消任务的操作； |
| public <T> Future<T> submit(Runnable task, T result) | Future<T>  | 提交Runnable任务并指定执行结果，只适用于执行任务的同时还要附带一个参数的场景，T是传入参数，也是返回结果 |
| public <T> Future<T> submit(Callable<T> task)        | Future<?>  | 提交Callable任务                                             |

**Future 可用方法：**

| 方法名                                                   | 描述                                                 |
| -------------------------------------------------------- | ---------------------------------------------------- |
| boolean cancel(boolean mayInterruptIfRunning);           | 是否取消任务，true取消，false不取消                  |
| boolean isCancelled();                                   | 任务是否取消                                         |
| boolean isDone();                                        | 任务是否完成                                         |
| V get() throws InterruptedException, ExecutionException; | 获取任务执行结果                                     |
| V get(long timeout, TimeUnit unit)                       | 在指定时间内获取任务执行结果，若超时则抛出超时异常； |

#### 代码演示：

```
/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 21:15
 */
public class ThreadPoolResultTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return 1 + 2;
	}
}
```



```

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 19:53
 */
public class ThreadPoolTester {

	public static void main(String[] args) {
		// 创建任务
		ThreadPoolTask task1 = new ThreadPoolTask();
		ThreadPoolTask task2 = new ThreadPoolTask();
		ThreadPoolTask task3 = new ThreadPoolTask();

		ThreadPoolResultTask resultTask = new ThreadPoolResultTask();

		/** 核心线程2个，最大线程4，存活时间10秒，存活时间10秒， */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(), 
				new ImplementThreadFactory(), 
				new ThreadPoolExecutor.AbortPolicy());

		// 提交任务
		try {
			// public Future<?> submit(Runnable task)
			Future<?> submit = threadPoolExecutor.submit(task1);
			System.out.println(submit.isDone());

			// public <T> Future<T> submit(Runnable task, T result)
			Future<String> submit2 = threadPoolExecutor.submit(task2, "我是附带参数");
			System.out.println(submit2.get());

			// public <T> Future<T> submit(Callable<T> task)
			Future<Integer> resultFuture = threadPoolExecutor.submit(resultTask);
			Integer result = resultFuture.get();
			System.out.println(result);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			threadPoolExecutor.shutdown();
		}
	}
}
```

### shutdown 和 shutdowNow 区别

|                            | shutdown | shutdownNow |
| -------------------------- | :------: | :---------: |
| 立即关闭线程池             |    ×     |      √      |
| 延迟关闭线程池             |    √     |      ×      |
| 不在接收新任务             |    √     |      √      |
| 继续执行完任务队列中的任务 |    √     |      ×      |
| 返回任务队列中的任务       |    ×     |      √      |
| 线程池状态                 | shutdown |    stop     |

### 线程池是怎样执行任务的？

