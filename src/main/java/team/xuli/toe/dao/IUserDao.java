package team.xuli.toe.dao;

import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
public interface IUserDao {
    User getByUsername(String username);

    boolean insert(User user);

    boolean disable(int userId);

    boolean update(User user);
}
