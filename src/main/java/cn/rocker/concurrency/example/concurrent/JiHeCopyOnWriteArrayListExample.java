package cn.rocker.concurrency.example.concurrent;

import cn.rocker.concurrency.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList：
 *      1:读写分离
 *      2.最终一致性
 * @author rocker
 * @date 2019/01/10 16:38
 * @since V1.0
 */
@ThreadSafe
public class JiHeCopyOnWriteArrayListExample {

    private static final Logger logger = LoggerFactory.getLogger(JiHeCopyOnWriteArrayListExample.class);

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
            final int count = i;
            executorService.execute(new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        update(count);
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

        logger.info("size:{}", list.size());
    }

    private static void update(int count){
        list.add(count);
    }

}
