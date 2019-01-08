package cn.rocker.concurrency.example.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean
 * @author rocker
 * @date 2019/01/08 16:13
 * @since V1.0
 */
public class AtomicExample6 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample6.class);

    private static AtomicBoolean flag = new AtomicBoolean(false);

    //请求总数
    public static int clientTotal = 5000;
    //并发的线程数
    public static int threadTotal = 200;

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
                        judge();
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
        logger.info("flag:{}", flag.get());
    }


    public static void judge(){
        if(flag.compareAndSet(false, true)){
            logger.info("execute");
        }
    }

}
