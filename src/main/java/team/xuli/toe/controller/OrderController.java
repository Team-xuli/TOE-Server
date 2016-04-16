package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.xuli.toe.domain.Order;
import team.xuli.toe.domain.ParamNewOrder;
import team.xuli.toe.domain.ParamOrderPage;
import team.xuli.toe.service.IOrderService;

import javax.validation.Valid;

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
    public boolean addOrder(@Valid @RequestBody ParamNewOrder paramNewOrder){
        return orderService.addOrder(paramNewOrder);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public Order deleteOrder(@PathVariable int orderId) {
        return orderService.deleteOrder(orderId);
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
    public ParamOrderPage getOrdersNearby(@Valid @RequestBody ParamOrderPage param){
        return orderService.getNewOrdersNearby(param);
    }

    @RequestMapping(value = "/order/history", method = RequestMethod.POST)
    public ParamOrderPage getHistoryOrders(@Valid @RequestBody ParamOrderPage param){
        return orderService.getHistoryOrders( param);
    }
}
