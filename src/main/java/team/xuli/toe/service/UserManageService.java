package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.xuli.toe.dao.IUserDao;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
@Service
public class UserManageService implements IUserManageService {
    @Autowired
    private IUserDao userDao;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("security!");
        return userDao.getUserByUsername(userName);
    }

}
