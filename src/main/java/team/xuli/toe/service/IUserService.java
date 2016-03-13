package team.xuli.toe.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
public interface IUserService extends UserDetailsService {
    UserDetails getUserByUsername(String username);
}
