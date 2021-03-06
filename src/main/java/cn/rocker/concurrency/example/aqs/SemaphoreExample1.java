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
public class SemaphoreExample1 {

    private static final Logger logger = LoggerFactory.getLogger(SemaphoreExample1.class);

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
                        semaphore.acquire();//获取许可
                        test(threadNum);
                        semaphore.release();//释放许可
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
        Thread.sleep(1000);
    }

}
