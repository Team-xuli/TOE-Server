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
    private IAddressService addressService;
    @Autowired
    private ISecurityService securityService;

    @Transactional
    public boolean addOrder(ParamNewOrder paramNewOrder){
        User currentUser = securityService.currentUser();
        if(paramNewOrder.isNewDestAddress()){
            addressService.validateNewAddress(paramNewOrder.getDestAddress());
        }
        //init new order
        Order order = new Order();
        //set default
        order.setOwnerId(currentUser.getUserId());
        order.setStatus(AppConst.ORDER_STATUS_NEW);
        order.setCreateTime(new Date());
        order.setDeadLine(paramNewOrder.getDeadLine());
        order.setPayment(paramNewOrder.getPayment());
        order.setDescription(paramNewOrder.getDescription());
        order.setOrgAddressId(paramNewOrder.getOrgAddressId());

        //转账给Admin
        securityService.remitToAdmin(currentUser.getUserId(), order.getPayment());

        //new destAddress
        if(paramNewOrder.isNewDestAddress()){
            if(!addressService.addDestAddress(paramNewOrder.getDestAddress()))
                return false;
            order.setDestAddressId(paramNewOrder.getDestAddress().getAddressId());
        } else {
            order.setDestAddressId(paramNewOrder.getDestAddressId());
        }
        return orderDao.insert(order);
    }

    public ParamOrderPage getHistoryOrders(ParamOrderPage param){
        User currentUser = securityService.currentUser();
        int ownerId = AppConst.ID_NONE_SENSE;
        int carrierId = AppConst.ID_NONE_SENSE;
        if (currentUser.getRole().equals(AppConst.ROLE_OWNER)) {
            ownerId = currentUser.getUserId();
        }else if (currentUser.getRole().equals(AppConst.ROLE_DELIVERER)) {
            carrierId = currentUser.getUserId();
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

    public boolean assignOrder(Order order){
        User currentUser = securityService.currentUser();
        Order tmpOrder = orderDao.get(order.getOrderId());

        this.isValidToAssign(tmpOrder);

        tmpOrder.setOrderId(order.getOrderId());
        tmpOrder.setCarrierId(currentUser.getUserId());
        tmpOrder.setStatus(AppConst.ORDER_STATUS_ASSIGNED);
        return orderDao.update(tmpOrder);
    }

    @Transactional
    public boolean closeOrder(Order order) {
        User currentUser = securityService.currentUser();
        //update order
        Order tmpOrder = orderDao.get(order.getOrderId());

        this.isValidToClose(currentUser, tmpOrder);

        tmpOrder.setOrderId(order.getOrderId());
        tmpOrder.setEndTime(new Date());
        tmpOrder.setStatus(AppConst.ORDER_STATUS_COMPLETED);
        orderDao.update(tmpOrder);

        //update account money and credit
        securityService.remitFromAdmin(order.getCarrierId(),order.getPayment());
        securityService.creditIncrease(order.getCarrierId(), AppConst.CREDIT_INCREMENT_FOR_DELIVERER);
        securityService.creditIncrease(order.getOwnerId(), AppConst.CREDIT_INCREMENT_FOR_OWNER);
        return true;
    }

    public Order deleteOrder(int orderId){
        User currentUser = securityService.currentUser();
        Order tmpOrder = orderDao.get(orderId);

        this.isValidToDelete(currentUser, tmpOrder);

        tmpOrder.setOrderId(orderId);
        tmpOrder.setStatus(AppConst.ORDER_STATUS_DELETED);
        orderDao.update(tmpOrder);
        return tmpOrder;
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
