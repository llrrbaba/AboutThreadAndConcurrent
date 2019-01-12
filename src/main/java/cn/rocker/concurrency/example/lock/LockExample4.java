package cn.rocker.concurrency.example.lock;


import cn.rocker.concurrency.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * TODO StampedLock有待研究源码
 * @author rocker
 * @date 2019/01/08 14:33
 * @since V1.0
 */
@ThreadSafe
public class LockExample4 {

    private static final Logger logger = LoggerFactory.getLogger(LockExample4.class);

    private static final StampedLock lock = new StampedLock();

    //请求总数
    public static int clientTotal = 5000;
    //并发的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义countDownLatch
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            executorService.execute(new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        logger.error("exception:{}", e);
                    }
                    countDownLatch.countDown();
                }
            });
        }

        //等待所有请求执行完毕，输出count值
        countDownLatch.await();
        executorService.shutdown();
        logger.info("count:{}", count);
    }

    private static void add(){
        long stamp = lock.writeLock();
        try {
            count++;
        } finally {
            lock.unlock(stamp);
        }
    }

}
