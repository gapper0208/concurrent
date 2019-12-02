Java中的线程池的创建其实非常灵活，我们可以通过配置不同的参数，创建出行为不同的线程池

这几个参数包括：
1. corePoolSize：线程池的核心线程数
2. maximumPoolSize：线程池允许的最大线程数
3. keepAliveTime：超过核心线程数时，闲置线程的存货时间
4. workQueue：任务执行前保存任务的队列，保存由execute方法提交的Runnable任务

设问：线程池中的线程时怎么创建的？是一开始就随着线程池的启动创建好的吗？
显然不是的。线程池默认初始化后，不会启动任何线程，直到有任务提交到该线程池时才会启动！

每当我们调用execute()方法添加一个任务时，线程池会做如下判断：
1. 如果正在运行的线程数小于corePoolSize，那么马上创建线程运行这个任务
2. 如果正在运行的线程数大于或等于corePoolSize，那么将这个任务放入队列
3. 如果这时候队列满了，而且正在运行的线程数小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务
4. 如果队列满了，而且正在运行的线程数大于或等于maximumPoolSize，那么线程池会抛出异常：RejectExecutionException

Java中默认实现好的线程池：
1. SingleThreadExecutor 
	这个线程池只有一个核心线程在工作，也就是相当于单线程串行执行所有任务。
	如果这个唯一的线程因为异常而结束，那么会有一个新的线程来替代它。此线程
	保证所有任务的执行顺序按照任务的提交顺序执行。
	
	corePoolSize：1，只有一个核心线程在工作
	maximumfPoolSize：1
	keepAliveTime：0L
	workQueue：缓冲队列是无界的
	
2. FixedThreadPool
	这个线程池是一个固定了线程数的线程池，只有核心线程。每次提交一个任务就创建一个线程，
	直到线程达到线程池的最大大小，如果某个线程因为执行异常而结束，那么线程池会
	补充一个新线程。
	
	corePoolSize: n
	maximumPoolSize: n
	keepAliveTime: 0L
	workQueue: 缓冲队列是无界的
	
3. CachedThreadPool 
	这个线程池是无界线程池，如果线程中的线程数超过了处理任务所需要的线程数，那么
	就会回收部分空闲（60秒不执行任务的）线程，当任务数超过线程池中的线程数时，
	此线程池又可以智能地添加新的线程来处理任务！
	
	corePoolSize: 0
	maximumPoolSize: Integer.MAX_VALUE
	keepAliveTime: 60L
	workQueue: 缓冲队列的缓冲区为1
	
4. ScheduledThreadPool
	核心线程数是固定的，最大线程数是无限的。此线程池支持定时以及周期性执行任务的
	需求。
	
	corePoolSize: n
	maximumPoolSize: Integer.MAX_VALUE
	keepAliveTime: DEFAULT_KEEPALIVE_MILLS
	workQueue: new DelayedWorkQueue()  延时队列
	

	