package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.service.IUserService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@RestController
@RequestMapping(value = "/user")
public class UserSignController {
    @Autowired
    IUserService userManageService;
    /**
     * 用户登录
     * @return 用户信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean userLongin() {
        return true;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public UserDetails getUserByUsername(@RequestParam String username){
        return userManageService.getUserByUsername(username);
    }

}
