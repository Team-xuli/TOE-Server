package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IRoleDao;
import team.xuli.toe.dao.IUserDao;
import team.xuli.toe.domain.*;
import team.xuli.toe.util.AppConst;
import team.xuli.toe.util.Messages;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IUserProfileService userProfileService;
    @Autowired
    private  ISecurityService securityService;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " login!");
        return userDao.getByUsername(username);
    }
    @Transactional
    public boolean signUpWithRole(ParamSignUp paramSignUp){
        this.identifyUsername(paramSignUp.getUsername());

        User user = newUser(paramSignUp.getUsername(),paramSignUp.getPassword());
        Role role = roleDao.getRoleByRoleName(paramSignUp.getRole());

        if(role != null &&  userDao.insert(user)) {
            RelationBtwUserAndRole relationBtwUserAndRole =
                    newRelationBtwUserAndRole(user.getUserId(), role.getRoleId());
            roleDao.insertRelationShip(relationBtwUserAndRole);
            userProfileService.createUserProfile(user,paramSignUp.getRole());
            return true;
        }else{
            return false;
        }
    }

    public boolean identifyUsername(String username){
        User user = userDao.getByUsername(username);
        if(user != null){
            throw new RuntimeException(Messages.USERNAME_ALREADY_EXISTS);
        }
        return true;
    }

    public boolean updateUser(User user){
        User currentUser = securityService.currentUser();
        User tmpUser = new User();
        tmpUser.setUserId(currentUser.getUserId());

        //changeable fields
        tmpUser.setUsername(user.getUsername());

        //validate and update
        this.validateUserModifier(currentUser, user);
        return userDao.update(tmpUser);
    }

    public boolean modifyPassword(ParamModifyPassword param){
        User currentUser = securityService.currentUser();
        User tmpUser = new User();
        tmpUser.setPassword(param.getNewPassword());
        this.validateOldPassword(currentUser,param.getOldPassword());
        return userDao.update(tmpUser);
    }

    private User newUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCredit(AppConst.INIT_CREDIT);
        user.setMoney(AppConst.INIT_MONEY);
        return user;
    }

    private RelationBtwUserAndRole newRelationBtwUserAndRole(int userId, int roleId) {
        RelationBtwUserAndRole relationBtwUserAndRole = new RelationBtwUserAndRole();
        relationBtwUserAndRole.setUserId(userId);
        relationBtwUserAndRole.setRoleId(roleId);
        return relationBtwUserAndRole;
    }

    public boolean validateUserModifier(User currentUser,User userInfo){
        if(userInfo.getUserId() != currentUser.getUserId()){
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
        return true;
    }

    public boolean validateOldPassword(User currentUser,String oldPassword){
        if(currentUser.getPassword().equals(oldPassword)){
            return true;
        }
        throw new RuntimeException(Messages.WRONG_PASS);
    }
}
