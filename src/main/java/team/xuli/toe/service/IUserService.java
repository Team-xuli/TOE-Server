package team.xuli.toe.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.xuli.toe.domain.ParamSignUp;
import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：
 */
public interface IUserService extends UserDetailsService {
    boolean signUpWithRole(ParamSignUp paramSignUp);

    boolean identifyUsername(String username);

    boolean validateUserModifier(User currentUser,User userInfo);

    boolean updateUser(User user);

}
