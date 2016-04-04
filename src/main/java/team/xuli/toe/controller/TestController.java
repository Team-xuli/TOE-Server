package team.xuli.toe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 徐清锋
 * 创建时间：2016/2/27
 * 创建原因：测试、模板控制类
 */
@RestController
public class TestController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloFunc(){
        return "hello world!";
    }

    @RequestMapping(value = "/shello",method = RequestMethod.GET)
    public String securityHelloFunc(){
        return "security hello world!";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws RuntimeException {
        return "hello index!";
    }
}
