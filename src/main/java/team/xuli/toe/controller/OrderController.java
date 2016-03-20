package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.domain.User;
import team.xuli.toe.service.IOrderService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public boolean addOrder(@RequestBody Order order){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.addOrder(currentUser,order);
    }
    @RequestMapping(value = "/historyOrders", method = RequestMethod.GET)
    public ParamOrderPage getHistoryOrders(@RequestBody ParamOrderPage param){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getPagingOrders(currentUser,param);
    }
}
