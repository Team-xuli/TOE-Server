package team.xuli.toe.domain;

import java.util.Date;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public class Order implements Comparable<Order> {
    private int orderId;
    private int ownerId;
    private int carrierId;

    private int orgAddressId;
    private Address orgAddress;

    private int destAddressId;
    private Address destAddress;

    private String description;
    private int itemTypeId;
    private ItemType itemType;

    private Double weight;
    private Double distance;

    private int payment;
    private int status;
    private Date createTime = null;
    private Date deadLine = null;
    private Date assignTime = null;
    private Date endTime = null;

    private Double interestScore = 0.0;

    public Address getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(Address orgAddress) {
        this.orgAddress = orgAddress;
    }

    public Address getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(Address destAddress) {
        this.destAddress = destAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Double getInterestScore() {
        return interestScore;
    }

    public void setInterestScore(Double interestScore) {
        this.interestScore = interestScore;
    }

    @Override
    public int compareTo(Order order) {
        if(order == null){
            return 0;
        }
        return order.getInterestScore().compareTo(this.getInterestScore());
    }
}

