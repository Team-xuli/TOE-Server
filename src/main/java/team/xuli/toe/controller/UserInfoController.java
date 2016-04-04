package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.domain.Address;
import team.xuli.toe.domain.ParamSignUp;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IAddressService;
import team.xuli.toe.service.IUserService;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@RestController
public class UserInfoController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public boolean signUp(@RequestBody ParamSignUp paramSignUp) throws RuntimeException {
        return userService.signUpWithRole(paramSignUp);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)userService.getUserByUsername(user.getUsername());
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.PUT)
    public boolean modifyUser(@RequestBody User user) throws RuntimeException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  userService.updateUser(currentUser, user);
    }

    @RequestMapping(value = "/user/address", method = RequestMethod.POST)
    public boolean addAddress(@RequestBody Address address)throws RuntimeException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return addressService.addOrgAddress(currentUser, address);
    }

    @RequestMapping(value = "/user/address", method = RequestMethod.PUT)
    public boolean updateAddress(@RequestBody Address address)throws RuntimeException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return addressService.updateOrgAddress(currentUser, address);
    }

    @RequestMapping(value = "/user/addresses", method = RequestMethod.GET)
    public List<Address> getAddresses()throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return addressService.getOrgAddresses(currentUser.getUserId());
    }

}
