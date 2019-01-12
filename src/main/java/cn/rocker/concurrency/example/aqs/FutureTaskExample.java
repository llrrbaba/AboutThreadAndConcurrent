package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.FutureTask;

/**
 * @author rocker
 * @date 2019/01/12 11:24
 * @since V1.0
 */
public class FutureTaskExample {

    private static final Logger logger = LoggerFactory.getLogger(FutureTaskExample.class);

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            logger.info("call in callable...");
            Thread.sleep(5000);
            return "Done!";
        });

        new Thread(futureTask).start();

        logger.info("call in main...");
        Thread.sleep(1000);
        String result = futureTask.get();
        logger.info("result:{}", result);
    }

}
