package cn.rocker.concurrency.example.commonUnsafe;

import cn.rocker.concurrency.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author rocker
 * @date 2019/01/10 15:55
 * @since V1.0
 */
@ThreadSafe
public class DateFormatExample2 {

    private static final Logger logger = LoggerFactory.getLogger(DateFormatExample2.class);

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
            executorService.execute(new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        update();
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
    }

    private static void update(){
        try {
            //把 SimpleDateFormat 设置成局部变量，每次调用都生成新的 SimpleDateFormat 对象
            //就不会有线程安全问题了
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            format.parse("20190110");
        } catch (ParseException e) {
            logger.error("parse exception:{}", e);
        }
    }

}
