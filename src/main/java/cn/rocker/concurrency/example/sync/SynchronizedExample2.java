package cn.rocker.concurrency.example.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized修饰的类或者静态方法
 *      针对该类的所有对象同步
 *      因为锁的监控对象是  类名.class
 * @author rocker
 * @date 2019/01/08 16:23
 * @since V1.0
 */
public class SynchronizedExample2 {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedExample2.class);

    //synchronized 修饰类
    public void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i=0;i<10;i++){
                logger.info("test1 - {} - {}", j, i);
            }
        }
    }


    //synchronized 修饰一个静态方法
    public static synchronized void test2(int j){
        for(int i=0;i<10;i++){
            logger.info("test2 - {} - {}", j, i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Thread(){
            @Override
            public void run() {
                example1.test1(1);
//                example1.test2(1);
            }
        });
        executorService.execute(new Thread(){
            @Override
            public void run() {
                example2.test1(2);
//                example2.test2(2);
            }
        });
    }

}
