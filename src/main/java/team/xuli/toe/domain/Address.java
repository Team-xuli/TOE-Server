package team.xuli.toe.domain;

import javax.validation.constraints.NotNull;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public class Address {
    private int addressId;
    private int userId;
    private int type;
    @NotNull
    private String calledName;
    @NotNull
    private String phoneNo;
    @NotNull
    private String addressDesc;
    @NotNull
    private String addressData = "";

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCalledName() {
        return calledName;
    }

    public void setCalledName(String calledName) {
        this.calledName = calledName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getAddressData() {
        return addressData;
    }

    public void setAddressData(String addressData) {
        this.addressData = addressData;
    }
}
