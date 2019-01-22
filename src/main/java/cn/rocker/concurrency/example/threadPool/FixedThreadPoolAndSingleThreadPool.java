package cn.rocker.concurrency.example.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO singleThreadPool和fixedThreadPool(1)的区别
 * @author rocker
 * @date 2019/01/22 13:31
 * @since V1.0
 */
public class FixedThreadPoolAndSingleThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(FixedThreadPoolAndSingleThreadPool.class);

    public static void main(String[] args) {

        ExecutorService executorService1 = Executors.newFixedThreadPool(3);

        for(int i=0; i<20; i++){
            final int index = i;
            executorService1.execute(() -> {
                logger.info("index:{},Executors.newFixedThreadPool(1),{}-{} is executing...", index,Thread.currentThread().getName(), Thread.currentThread().getId());
            });
        }


//        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
//
//        for(int i=0; i<1000; i++){
//            executorService2.execute(() -> {
//                logger.info("Executors.newSingleThreadExecutor(),{} is  executing...", Thread.currentThread().getName());
//            });
//        }
    }

}
