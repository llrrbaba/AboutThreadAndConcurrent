package cn.rocker.concurrency.example.lock;


import cn.rocker.concurrency.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author rocker
 * @date 2019/01/08 14:33
 * @since V1.0
 */
@ThreadSafe
public class LockExample3 {

    private static final Logger logger = LoggerFactory.getLogger(LockExample3.class);

    private final Map<String, Data> map = new TreeMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    class Data{}

    public Data get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data data){
        writeLock.lock();
        try {
            return map.put(key, data);
        } finally {
            writeLock.unlock();
        }
    }

}
