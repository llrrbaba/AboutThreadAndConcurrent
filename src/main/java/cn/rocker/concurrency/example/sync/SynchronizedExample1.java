package cn.rocker.concurrency.example.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized修饰的代码块或者方法
 *      针对同一个对象同步
 *      针对不同对象不同步
 *      因为锁的监控对象是this
 * @author rocker
 * @date 2019/01/08 16:23
 * @since V1.0
 */
public class SynchronizedExample1 {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedExample1.class);

    //synchronized 修饰代码块
    public void test1(int j){
        synchronized (this){
            for(int i=0;i<10;i++){
                logger.info("test1 - {} - {}", j, i);
            }
        }
    }


    //synchronized 修饰一个方法
    public synchronized void test2(){
        for(int i=0;i<10;i++){
            logger.info("test2 - {}", i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Thread(){
            @Override
            public void run() {
                example1.test1(1);
//                example1.test2();
            }
        });
        executorService.execute(new Thread(){
            @Override
            public void run() {
                example2.test1(2);
//                example1.test2();
            }
        });
    }

}
