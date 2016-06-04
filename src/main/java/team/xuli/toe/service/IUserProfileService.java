package team.xuli.toe.service;

import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.User;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
public interface IUserProfileService {
    int createUserProfile(User user, String role);
    int updateUserProfile(User user, Order order);
    int sortNearByOrder(User user, List<Order> orders);
}
