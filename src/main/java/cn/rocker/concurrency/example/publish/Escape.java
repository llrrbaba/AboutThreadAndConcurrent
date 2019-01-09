package cn.rocker.concurrency.example.publish;

import cn.rocker.concurrency.annotations.NotRecommand;
import cn.rocker.concurrency.annotations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rocker
 * @date 2019/01/09 20:42
 * @since V1.0
 */
@NotThreadSafe
@NotRecommand
public class Escape {

    private static final Logger logger = LoggerFactory.getLogger(Escape.class);

    private int thisCanEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            //对象逸出
            //这里在内部类中可以访问的外部类的成员，然而此时，外部类还没构造完成
            //这样可能会导致不安全的影响
            logger.info("thisCanEscape:{}", Escape.this.thisCanEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
