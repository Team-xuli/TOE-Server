package team.xuli.toe.domain;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/20
 * 创建原因：
 */
public class ParamOrderPage {
    private int pageNo;
    private int ordersCount;
    private int countPerPage;
    private int status = -1;
    private List<Order> orders;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
