package team.xuli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.service.ITestService;

/**
 * @author: 徐清锋
 * 创建时间：2016/2/27
 * 创建原因：测试、模板控制类
 */
@RestController
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloFun(){
        return "hello world!";
    }
}
