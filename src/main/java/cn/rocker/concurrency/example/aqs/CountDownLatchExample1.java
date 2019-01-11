package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch用法演示1
 *      规定次数
 * @author rocker
 * @date 2019/01/11 13:40
 * @since V1.0
 */
public class CountDownLatchExample1 {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchExample1.class);

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final  int threadNum = i;
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

        countDownLatch.await();
        logger.info("finish...");

        //如果线程池不再使用了，记得将线程池关闭
        threadPool.shutdown();
    }


    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        logger.info("threadNum:{}", threadNum);
        Thread.sleep(100);
    }
}
