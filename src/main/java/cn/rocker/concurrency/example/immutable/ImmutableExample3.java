package cn.rocker.concurrency.example.immutable;

import cn.rocker.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @author rocker
 * @date 2019/01/09 22:09
 * @since V1.0
 */
@ThreadSafe
public class ImmutableExample3 {

    private static final ImmutableList list = ImmutableList.of(1,2,3);

    private static final ImmutableSet set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4,5,6);

    public static void main(String[] args) {
        //运行期都会抛出异常
        list.add(4);
        set.add(4);
        map.put(7,8);
    }
}
