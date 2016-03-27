package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IRoleDao;
import team.xuli.toe.dao.IUserDao;
import team.xuli.toe.domain.ParamSignUp;
import team.xuli.toe.domain.RelationBtwUserAndRole;
import team.xuli.toe.domain.Role;
import team.xuli.toe.domain.User;
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

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("One login!");
        return userDao.getByUsername(username);
    }

    @Transactional
    public boolean signUpWithRole(ParamSignUp paramSignUp){
        User user = newUser(paramSignUp.getUsername(),paramSignUp.getPassword());
        Role role = roleDao.getRoleByRoleName(paramSignUp.getRole());

        if(role != null &&  userDao.insert(user)) {
            RelationBtwUserAndRole relationBtwUserAndRole =
                    newRelationBtwUserAndRole(user.getUserId(), role.getRoleId());
            return roleDao.insertRelationShip(relationBtwUserAndRole);
        }else{
            return false;
        }
    }

    public boolean validateUsername(String username){
        User user = userDao.getByUsername(username);
        if(user != null){
            throw new RuntimeException(Messages.USERNAME_ALREADY_EXISTS);
        }
        return true;
    }

    public boolean updateUser(User user){
        return userDao.update(user);
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
}
