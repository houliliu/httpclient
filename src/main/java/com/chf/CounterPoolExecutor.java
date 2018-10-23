package com.chf;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ${DESCRIPTION}
 *
 * @author 温柔一刀
 * @create 2018-10-23 22:45
 **/
public class CounterPoolExecutor extends ThreadPoolExecutor {
    private AtomicInteger count = new AtomicInteger(0);//统计执行次数
    private long startTime = System.currentTimeMillis();
    private String funcname = "";
    private final static int COUNT = 100;

    public CounterPoolExecutor(int corePoolSize, int maximumPoolSize,
                               long keepAliveTime, TimeUnit unit,
                               BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CounterPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), handler);
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t) {//线程执行结束时
        int l = count.addAndGet(1);
        if (l == COUNT) {
            System.out.println(funcname + "spend time:" + (System.currentTimeMillis() - startTime));
        }
    }
}
