package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author rocker
 * @date 2019/01/12 11:13
 * @since V1.0
 */
public class FutureExample {

    private static final Logger logger = LoggerFactory.getLogger(FutureExample.class);

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            logger.info("call in callable...");
            Thread.sleep(5000);
            return "Done!";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<String> future = threadPool.submit(new MyCallable());
        logger.info("call in main...");
        Thread.sleep(1000);
        String result = future.get();
        logger.info("result:{}", result);
    }

}
