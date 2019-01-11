package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author rocker
 * @date 2019/01/11 15:01
 * @since V1.0
 */
public class CyclicBarrierExample2 {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExample2.class);

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            Thread.sleep(1000);

            final int threadNum = i;
            threadPool.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    logger.error("e:{}", e);
                }
            });
        }

        threadPool.shutdown();

    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        logger.info("threadNum:{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        logger.info("threadNum:{} is continued", threadNum);
    }

}
