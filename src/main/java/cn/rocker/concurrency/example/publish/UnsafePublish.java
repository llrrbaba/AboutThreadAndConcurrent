package cn.rocker.concurrency.example.publish;

import cn.rocker.concurrency.annotations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 不安全地发布对象
 * @author rocker
 * @date 2019/01/08 17:23
 * @since V1.0
 */
@NotThreadSafe
public class UnsafePublish {

    private static final Logger logger = LoggerFactory.getLogger(UnsafePublish.class);

    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        logger.info("states:{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        logger.info("states:{}", Arrays.toString(unsafePublish.getStates()));
    }
}
