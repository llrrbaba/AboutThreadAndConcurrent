package cn.rocker.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rocker
 * @date 2019/01/12 10:51
 * @since V1.0
 */
public class ConditionExample1 {

    private static final Logger logger = LoggerFactory.getLogger(ConditionExample1.class);

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                logger.info("waiting signal...");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("got signal...");
            lock.unlock();
        }).start();

        new Thread(()->{
            lock.lock();
            logger.info("got lock...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            logger.info("sending signal...");
            lock.unlock();
        }).start();
    }

}
