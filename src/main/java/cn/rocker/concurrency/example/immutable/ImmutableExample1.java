package cn.rocker.concurrency.example.immutable;

import cn.rocker.concurrency.annotations.NotThreadSafe;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * final
 * 不可变
 * 基础类型
 * 引用类型
 * @author rocker
 * @date 2019/01/09 21:43
 * @since V1.0
 */
@NotThreadSafe
public class ImmutableExample1 {

    private static final int a = 1;
    private static final String b = "2";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();

        //map引用不可以指向新的实例，但是可以往map里put新的key-value
        map.put(1,3);
        System.out.println(map.get(1));
    }


    //TODO final还可以修饰入参
    public void testRuCan(final int a){
//        a = 3;
    }

}
