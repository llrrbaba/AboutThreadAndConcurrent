package cn.rocker.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author rocker
 * @date 2019/01/11 14:36
 * @since V1.0
 */
public class SemaphoreExample3 {

    private static final Logger logger = LoggerFactory.getLogger(SemaphoreExample3.class);

    private final static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //控制并发数为3
        final Semaphore semaphore = new Semaphore(3);

        for(int i=0;i<threadCount;i++){
            final  int threadNum = i;
            threadPool.execute(new Thread(){
                @Override
                public void run() {
                    try {
                        if(semaphore.tryAcquire()){//尝试获取一个许可，如果获取到就执行，获取不到则放弃
                            test(threadNum);
                            semaphore.release();
                        }
                    } catch (InterruptedException e) {
                        logger.error("e:{}", e);
                    }
                }
            });
        }

        //如果线程池不再使用了，记得将线程池关闭
        threadPool.shutdown();
    }


    public static void test(int threadNum) throws InterruptedException {
        logger.info("threadNum:{}", threadNum);
    }

}
