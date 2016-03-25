package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IAddressService;
import team.xuli.toe.service.IOrderService;
import team.xuli.toe.util.Messages;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public boolean addOrder(@RequestBody ParamNewOrder paramNewOrder){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(paramNewOrder.isNewDestAddress() && !addressService.validateNewAddress(paramNewOrder.getDestAddress())) {
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
        return orderService.addOrder(currentUser,paramNewOrder);
    }
    @RequestMapping(value = "/order/history", method = RequestMethod.POST)
    public ParamOrderPage getHistoryOrders(@RequestBody ParamOrderPage param){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getPagingOrders(currentUser,param);
    }
}
