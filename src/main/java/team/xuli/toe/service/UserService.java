package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.xuli.toe.dao.IUserDao;
import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("One login!");
        return userDao.getByUsername(username);
    }

    public boolean signUp(User user){
        user.setCredit(1);
        user.setMoney(0);
        return userDao.insert(user);
    }

    public boolean validateUsername(String username){
        User user = userDao.getByUsername(username);
        return user == null || user.getUserId() == 0;
    }

    public boolean updateUser(User user){
        return userDao.update(user);
    }
}
