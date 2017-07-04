package com.pw.util.thread;

import com.pw.util.exception.PwRuntimeException;

import java.util.concurrent.*;

/**
 * Created by PoemWhite on 2017/4/24.
 */
public class ExecutorServiceManager {


    private int corePoolSize=30;
    private int maximumPoolSize=-1;
    private long keepAliveTime=100;
    private int workQueueSize=0;
    private TimeUnit timeUnit=TimeUnit.MILLISECONDS;
    private BlockingQueue<Runnable> workQueue=null;
    private RejectedExecutionHandler handler=new ThreadPoolExecutor.CallerRunsPolicy();

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueueSize() {
        return workQueueSize;
    }

    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public void setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
    }

    public ExecutorService create(){
        ExecutorService executorService = null;
        if(maximumPoolSize<=0){
            maximumPoolSize=Integer.MAX_VALUE;
        }
        if(workQueue==null){
            if(workQueueSize<=0){
                workQueue=new LinkedBlockingQueue<Runnable>();
                workQueueSize=Integer.MAX_VALUE;
            }else{
                workQueue=new LinkedBlockingQueue<Runnable>(workQueueSize);
            }
        }
        executorService = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                workQueue,
                handler);
        return executorService;
    }

    /**
     * 发起关闭线程池命令并等待任务执行完成
     * @param exec
     * @param awaitTime
     * @throws PwRuntimeException
     */
    public static void shutdownAndAwaitTermination(ExecutorService exec,long awaitTime) throws PwRuntimeException {
        exec.shutdown();
        try {
            //等待任务全部完成或达到指定时间
            if(!exec.awaitTermination(awaitTime, TimeUnit.SECONDS)){
                //如果不是由于任务全部完成，而是由于到达指定时间而停止等待的，立刻shutdown，取消现有任务
                exec.shutdownNow();
                // 再多等一分钟，如果在一分钟内还没有处理好遗留任务，日志输出错误
                if(!exec.awaitTermination(60, TimeUnit.SECONDS)){
                    throw new PwRuntimeException("exec did not terminate");
                }
            }
        } catch (InterruptedException e) {

            exec.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
