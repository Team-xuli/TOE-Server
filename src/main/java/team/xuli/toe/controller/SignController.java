package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.domain.ParamSignUp;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IUserService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/27
 * 创建原因：
 */
@RestController
public class SignController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public boolean signUp(@RequestBody ParamSignUp paramSignUp) throws RuntimeException {
        return userService.validateUsername(paramSignUp.getUsername()) && userService.signUpWithRole(paramSignUp);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)userService.loadUserByUsername(user.getUsername());
    }
}
