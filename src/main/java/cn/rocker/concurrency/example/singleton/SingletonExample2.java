package cn.rocker.concurrency.example.singleton;

import cn.rocker.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 实例类装载时实例化
 * @author rocker
 * @date 2019/01/09 21:01
 * @since V1.0
 */
@ThreadSafe
public class SingletonExample2 {

    //私有的构造函数
    private SingletonExample2() {
    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法，获取单例对象
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
