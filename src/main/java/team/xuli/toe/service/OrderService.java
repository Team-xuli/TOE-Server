package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.xuli.toe.dao.IOrderDao;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    public boolean addOrder(User user,Order order){
        order.setOwnerId(user.getUserId());
        return orderDao.insert(order);
    }

    public ParamOrderPage getPagingOrders(User user,ParamOrderPage param){
        int ownerId = AppConst.ID_NONE_SENSE;
        int carrierId = AppConst.ID_NONE_SENSE;
        if (user.getRole() == AppConst.ROLE_OWNER) {
            ownerId = user.getUserId();
        }else if (user.getRole() == AppConst.ROLE_CARRIER) {
            carrierId = user.getUserId();
        }
        param.setOrdersCount(orderDao.getQualifiedOrdersCount(ownerId,
                                                              carrierId,
                                                              param.getStatus()));
        param.setOrders(orderDao.getQualifiedOrders((param.getPageNo() - 1) * param.getCountPerPage(),
                                                    param.getCountPerPage(),
                                                    ownerId,
                                                    carrierId,
                                                    param.getStatus()));
        return param;
    }
}
