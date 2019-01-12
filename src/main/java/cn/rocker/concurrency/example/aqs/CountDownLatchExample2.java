package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch用法演示2
 *      规定时间
 * @author rocker
 * @date 2019/01/11 13:40
 * @since V1.0
 */
public class CountDownLatchExample2 {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchExample2.class);

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){

            final int threadNum = i;
            threadPool.execute(new Thread(){
                @Override
                public void run() {
                    try {
                        test(threadNum);
                    } catch (InterruptedException e) {
                        logger.error("e:{}", e);
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        logger.info("finish...");

        //如果线程池不再使用了，记得将线程池关闭
        threadPool.shutdown();
    }


    public static void test(int threadNum) throws InterruptedException {
        //TODO 这里要注意线程睡眠要在子线程内，否则不会对
        //TODO countDownLatch.await(10, TimeUnit.MILLISECONDS);这句话生效
        Thread.sleep(100);
        logger.info("threadNum:{}", threadNum);
    }
}
