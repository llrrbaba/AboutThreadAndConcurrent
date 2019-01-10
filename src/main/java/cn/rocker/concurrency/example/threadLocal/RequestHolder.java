package cn.rocker.concurrency.example.threadLocal;

/**
 * @author rocker
 * @date 2019/01/10 14:26
 * @since V1.0
 */
public class RequestHolder {

    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(long id){
        requestHolder.set(id);
    }

    public static long get(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }

}
