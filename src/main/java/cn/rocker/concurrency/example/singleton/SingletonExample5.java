package cn.rocker.concurrency.example.singleton;

import cn.rocker.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 实例在第一次获取的时候实例化
 * @author rocker
 * @date 2019/01/09 21:01
 * @since V1.0
 */
@ThreadSafe
public class SingletonExample5 {

    //私有的构造函数
    private SingletonExample5() {
    }

    //单例对象
    //这里是 volatile 的一个经典使用场景
    //禁止指令重排
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法，获取单例对象
    public static synchronized SingletonExample5 getInstance(){
        //双重检测机制 + synchronized
        if(instance == null){
            synchronized (SingletonExample5.class){
                if(instance == null){
                    //下面这行代码，包含了三个过程
                    //1.分配对象的内存空间
                    //2.初始化对象
                    //3.设置引用instance指向刚分配的内存空间
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
