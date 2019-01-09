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
public class SingletonExample4 {

    //私有的构造函数
    private SingletonExample4() {
    }

    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法，获取单例对象
    public static synchronized SingletonExample4 getInstance(){
        //双重检测机制 + synchronized
        if(instance == null){
            synchronized (SingletonExample4.class){
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
