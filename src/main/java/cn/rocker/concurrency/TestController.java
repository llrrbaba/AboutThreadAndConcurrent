package cn.rocker.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rocker
 * @date 2019/01/08 13:59
 * @since V1.0
 */
@Controller
public class TestController {

    @RequestMapping
    @ResponseBody
    public String test(){
        return "test";
    }

}
