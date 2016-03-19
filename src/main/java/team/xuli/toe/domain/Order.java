package team.xuli.toe.domain;

import java.util.Date;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public class Order {
    private int orderId;
    private int carrierId;
    private int orgAddressId;
    private int destAddressId;
    private String description;
    private int payment;
    private int status;
    private Date createTime;
    private Date deadLine;
    private Date assignTime;
    private Date endTime;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    public int getOrgAddressId() {
        return orgAddressId;
    }

    public void setOrgAddressId(int orgAddressId) {
        this.orgAddressId = orgAddressId;
    }

    public int getDestAddressId() {
        return destAddressId;
    }

    public void setDestAddressId(int destAddressId) {
        this.destAddressId = destAddressId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

