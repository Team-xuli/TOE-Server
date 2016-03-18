package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IUserService;
import team.xuli.toe.util.Messages;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@RestController
@RequestMapping(value = "/user")
public class UserSignController {
    @Autowired
    IUserService userService;
    /**
     * 用户登录
     * @return 用户信息
     */
    @RequestMapping(value = "/passport", method = RequestMethod.GET)
    public boolean signIn() {
        return true;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean signUp(@RequestBody User user) throws RuntimeException{
        if(userService.validateUsername(user.getUsername())){
            return userService.signUp(user);
        }else{
            throw new RuntimeException(Messages.USERNAME_ALREADY_EXISTS);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public boolean modifyUser(@RequestBody User user) throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getUserId() == currentUser.getUserId()){
            return userService.updateUser(user);
        }else{
            throw new RuntimeException(Messages.MODIFY_WRONG_USER);
        }

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)userService.loadUserByUsername(user.getUsername());
    }

}
