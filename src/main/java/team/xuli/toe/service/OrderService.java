package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IOrderDao;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;

import java.util.Date;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IAddressService addressService;

    @Transactional
    public boolean addOrder(User user,ParamNewOrder paramNewOrder){
        //init new order
        Order order = new Order();
        //set default
        order.setOwnerId(user.getUserId());
        order.setStatus(AppConst.ORDER_SRARUS_NEW);
        order.setCreateTime(new Date());
        order.setDeadLine(paramNewOrder.getDeadLine());
        order.setPayment(paramNewOrder.getPayment());
        order.setDescription(paramNewOrder.getDescription());
        order.setOrgAddressId(paramNewOrder.getOrgAddressId());
        //new destAddress
        if(paramNewOrder.isNewDestAddress()){
            if(!addressService.addDestAddress(user,paramNewOrder.getDestAddress()))
                return false;
            order.setDestAddressId(paramNewOrder.getDestAddress().getAddressId());
        } else {
            order.setDestAddressId(paramNewOrder.getDestAddressId());
        }
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
