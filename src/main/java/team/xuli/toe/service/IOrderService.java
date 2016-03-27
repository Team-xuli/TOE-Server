package team.xuli.toe.service;

import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
public interface IOrderService {
    ParamOrderPage getHistoryOrders(User user, ParamOrderPage param);
    ParamOrderPage getNewOrdersNearby(ParamOrderPage param);
    boolean addOrder(User user,ParamNewOrder paramNewOrder);
    boolean assignOrder(User user,Order order);
    boolean closeOrder(Order order);
    boolean deleteOrder(Order order);
    boolean validateOrderModifier(User user, Order order);
    boolean isValidToAssign(Order order);
    boolean isValidToClose(User user,Order order);
    boolean isValidToDelete(User user,Order order);
}
