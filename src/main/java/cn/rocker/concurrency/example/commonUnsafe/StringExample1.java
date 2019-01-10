package cn.rocker.concurrency.example.commonUnsafe;

import cn.rocker.concurrency.annotations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * StringBuilder 的线程安全问题
 * @author rocker
 * @date 2019/01/10 15:20
 * @since V1.0
 */
@NotThreadSafe
public class StringExample1 {

    private static final Logger logger = LoggerFactory.getLogger(StringExample1.class);

    //请求总数
    public static int clientTotal = 5000;
    //并发的线程数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

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
                        update();
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

        //如果 StringBuilder是线程安全的话，输出的长度应该恒为5000才对
        logger.info("length:{}", stringBuilder.length());
    }

    private static void update(){
        stringBuilder.append("1");
    }

}
