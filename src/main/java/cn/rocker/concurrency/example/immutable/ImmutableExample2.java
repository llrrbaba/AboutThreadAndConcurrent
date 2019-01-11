package cn.rocker.concurrency.example.immutable;

import cn.rocker.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiable...实现不可变
 * 不可变
 * 基础类型
 * 引用类型
 * @author rocker
 * @date 2019/01/09 21:43
 * @since V1.0
 */
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
//        map = Maps.newHashMap();

        //map引用可以指向新的实例，但是不可以往map里put新的key-value
        map.put(1,3);
        System.out.println(map.get(1));
    }

}
