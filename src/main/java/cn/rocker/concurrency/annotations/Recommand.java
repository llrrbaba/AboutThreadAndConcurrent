package cn.rocker.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个注解
 * 用来标记【推荐】的类
 * @author rocker
 * @date 2019/01/08 13:52
 * @since V1.0
 */
@Target(ElementType.TYPE)//作用在类上
@Retention(RetentionPolicy.SOURCE)//只是在代码中看到，作为标记，在编译时就会被忽略，不会影响代码的编译期|运行期效果
public @interface Recommand {
}
