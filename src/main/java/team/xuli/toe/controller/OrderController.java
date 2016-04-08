package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.service.IOrderService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public boolean addOrder(@RequestBody ParamNewOrder paramNewOrder){
        return orderService.addOrder(paramNewOrder);
    }

    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    public boolean deleteOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order);
    }

    @RequestMapping(value = "/order/assignment", method = RequestMethod.POST)
    public boolean assignOrder(@RequestBody Order order) throws RuntimeException {
        return orderService.assignOrder(order);
    }

    @RequestMapping(value = "/order/achievement", method = RequestMethod.POST)
    public boolean closeOrder(@RequestBody Order order) throws RuntimeException {
        return orderService.closeOrder(order);
    }

    @RequestMapping(value = "/order/nearby", method = RequestMethod.POST)
    public ParamOrderPage getOrdersNearby(@RequestBody ParamOrderPage param){
        return orderService.getNewOrdersNearby(param);
    }

    @RequestMapping(value = "/order/history", method = RequestMethod.POST)
    public ParamOrderPage getHistoryOrders(@RequestBody ParamOrderPage param){
        return orderService.getHistoryOrders( param);
    }
}
