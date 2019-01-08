package cn.rocker.concurrency.example.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference
 * @author rocker
 * @date 2019/01/08 15:51
 * @since V1.0
 */
public class AtomicExample4 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample4.class);

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);

        logger.info("count:{}", count);//4
    }

}
