package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author rocker
 * @date 2019/01/11 15:01
 * @since V1.0
 */
public class CyclicBarrierExample3 {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExample3.class);

    //TODO barrierAction是当指定的线程都处于barrier状态时首先执行的任务
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
        logger.info("callback is running");
    });

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
        cyclicBarrier.await();
        logger.info("threadNum:{} is continued", threadNum);
    }

}
