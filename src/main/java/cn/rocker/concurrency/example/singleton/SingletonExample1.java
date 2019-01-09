package cn.rocker.concurrency.example.singleton;

import cn.rocker.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 实例在第一次获取的时候实例化
 * @author rocker
 * @date 2019/01/09 21:01
 * @since V1.0
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有的构造函数
    private SingletonExample1() {
    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法，获取单例对象
    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
