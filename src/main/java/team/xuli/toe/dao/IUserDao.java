package team.xuli.toe.dao;

import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
public interface IUserDao {
    User getUserByUsername(String username);
}
