package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IOrderDao;
import team.xuli.toe.dao.IUserDao;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;
import team.xuli.toe.util.Messages;

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
    private IUserDao userDao;
    @Autowired
    private IAddressService addressService;

    @Transactional
    public boolean addOrder(User user,ParamNewOrder paramNewOrder){
        //init new order
        Order order = new Order();
        //set default
        order.setOwnerId(user.getUserId());
        order.setStatus(AppConst.ORDER_STATUS_NEW);
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

    public ParamOrderPage getHistoryOrders(User user,ParamOrderPage param){
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

    public ParamOrderPage getNewOrdersNearby(ParamOrderPage param){
        int ownerId = AppConst.ID_NONE_SENSE;
        int carrierId = AppConst.ID_NONE_SENSE;
        //状态设为新订单
        param.setOrdersCount(orderDao.getQualifiedOrdersCount(ownerId,
                carrierId,
                AppConst.ORDER_STATUS_NEW));
        param.setOrders(orderDao.getQualifiedOrders((param.getPageNo() - 1) * param.getCountPerPage(),
                param.getCountPerPage(),
                ownerId,
                carrierId,
                AppConst.ORDER_STATUS_NEW));
        return param;
    }

    public boolean assignOrder(User user,Order order){
        Order tmpOrder = new Order();
        tmpOrder.setOrderId(order.getOrderId());
        tmpOrder.setCarrierId(user.getUserId());
        tmpOrder.setStatus(AppConst.ORDER_STATUS_ASSIGNED);
        return orderDao.update(order);
    }

    @Transactional
    public boolean closeOrder(Order order){
        //update order
        Order tmpOrder = new Order();
        tmpOrder.setOrderId(order.getOrderId());
        tmpOrder.setEndTime(new Date());
        tmpOrder.setStatus(AppConst.ORDER_STATUS_COMPLETED);
        orderDao.update(order);

        //update account money and credit
        Order currentOrder = orderDao.get(order.getOrderId());
        User deliverer = userDao.getById(currentOrder.getCarrierId());
        User owner = userDao.getById(currentOrder.getOwnerId());

        deliverer.setMoney(deliverer.getMoney() + currentOrder.getPayment());
        deliverer.setCredit(deliverer.getCredit() + AppConst.CREDIT_INCREMENT_FOR_DELIVERER);
        owner.setMoney(owner.getMoney() - currentOrder.getPayment());
        owner.setCredit(owner.getCredit() + AppConst.CREDIT_INCREMENT_FOR_OWNER);

        userDao.update(deliverer);
        userDao.update(owner);
        return true;
    }

    public boolean deleteOrder(Order order) throws RuntimeException{
        Order tmpOrder = new Order();
        tmpOrder.setOrderId(order.getOrderId());
        tmpOrder.setStatus(AppConst.ORDER_STATUS_DELETED);
        return orderDao.update(order);
    }
    public boolean validateOrderModifier(User user, Order order) {
        Order oldOrder = orderDao.get(order.getOrderId());
        if (oldOrder.getOwnerId() != user.getUserId()){
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
        return true;
    }

    public boolean isValidToDelete(User user,Order order) {
        if(validateOrderModifier(user,order)) {
            Order oldOrder = orderDao.get(order.getOrderId());
            if (oldOrder.getStatus() != AppConst.ORDER_STATUS_NEW) {
                throw new RuntimeException(Messages.DELETE_ORDER_FAILED_FOR_STATUS);
            }
        }
        return true;
    }
    public boolean isValidToAssign(Order order){
        Order oldOrder = orderDao.get(order.getOrderId());
        if(oldOrder.getStatus() != AppConst.ORDER_STATUS_NEW){
            throw new RuntimeException(Messages.ASSIGN_ORDER_FAILED_FOR_STATUS);
        }
        return true;
    }

    public boolean isValidToClose(User user,Order order){
        Order oldOrder = orderDao.get(order.getOrderId());

        if(oldOrder.getCarrierId() != user.getUserId()){
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
        if(oldOrder.getStatus() != AppConst.ORDER_STATUS_ASSIGNED){
            throw new RuntimeException(Messages.CLOSE_ORDER_FAILED_FOR_STATUS);
        }
        return true;
    }

}
