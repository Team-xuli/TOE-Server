package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team.xuli.toe.domain.Address;
import team.xuli.toe.domain.ParamSignUp;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IAddressService;
import team.xuli.toe.service.IUserService;
import team.xuli.toe.util.Messages;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    IUserService userService;
    @Autowired
    IAddressService addressService;

    @RequestMapping(value = "/passport", method = RequestMethod.GET)
    public String signIn() {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRole();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean signUp(@RequestBody ParamSignUp paramSignUp,@RequestParam String roleName) throws RuntimeException{
        if(userService.validateUsername(paramSignUp.getUsername())){
            return userService.signUpWithRole(paramSignUp);
        }else{
            throw new RuntimeException(Messages.USERNAME_ALREADY_EXISTS);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)userService.loadUserByUsername(user.getUsername());
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public boolean modifyUser(@RequestBody User user) throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getUserId() == currentUser.getUserId()){
            return userService.updateUser(user);
        }else{
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }

    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public boolean addAddress(@RequestBody Address address)throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(address.getUserId() == currentUser.getUserId()){
            return addressService.addOrgAddress(address);
        }else{
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
    }

    @RequestMapping(value = "/address", method = RequestMethod.PUT)
    public boolean updateAddress(@RequestBody Address address)throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(addressService.validateAddressModifier(currentUser,address)){
            return addressService.updateOrgAddress(address);
        }else{
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
    }

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public List<Address> getAddresses()throws RuntimeException{
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return addressService.getOrgAddresses(currentUser.getUserId());
    }

}
