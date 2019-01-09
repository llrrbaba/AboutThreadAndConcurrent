package cn.rocker.concurrency.example.singleton;

import cn.rocker.concurrency.annotations.NotRecommand;
import cn.rocker.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 实例在第一次获取的时候实例化
 * @author rocker
 * @date 2019/01/09 21:01
 * @since V1.0
 */
@ThreadSafe
@NotRecommand
public class SingletonExample3 {

    //私有的构造函数
    private SingletonExample3() {
    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法，获取单例对象
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
