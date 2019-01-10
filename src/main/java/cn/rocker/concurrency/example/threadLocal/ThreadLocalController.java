package cn.rocker.concurrency.example.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rocker
 * @date 2019/01/10 15:00
 * @since V1.0
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    public long test(){
        return RequestHolder.get();
    }

}
