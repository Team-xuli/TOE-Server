package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.service.IUserManageService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@RestController
public class UserSignController {
    @Autowired
    IUserManageService userManageService;
    /**
     * 用户登陆
     * @return 用户信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean userLongin() {
        return true;
    }

}
