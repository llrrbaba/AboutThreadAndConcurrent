package cn.rocker.concurrency.example.atomic;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater
 * @author rocker
 * @date 2019/01/08 15:59
 * @since V1.0
 */
public class AtomicExample5 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample5.class);

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    private volatile int count = 100;

    private static AtomicExample5 atomicExample5 = new AtomicExample5();

    public static void main(String[] args) {
        if(updater.compareAndSet(atomicExample5, 100, 120)){
            logger.info("update success, count:{}", atomicExample5.getCount());
        }

        if(updater.compareAndSet(atomicExample5, 100, 120)){
            logger.info("update success, count:{}", atomicExample5.getCount());
        }else{
            logger.info("update fail");
        }
    }


    public int getCount() {
        return count;
    }

}
