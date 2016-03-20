package team.xuli.toe.service;

import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
public interface IOrderService {
    ParamOrderPage getPagingOrders(User user, ParamOrderPage param);
    boolean addOrder(User user,Order order);
}
