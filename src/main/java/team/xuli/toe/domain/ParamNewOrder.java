package team.xuli.toe.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/25
 * 创建原因：
 */
public class ParamNewOrder {

    @Min(1)
    private int orgAddressId;

    @NotNull
    @NotBlank
    private String description;

    @Min(1)
    private int payment = 0;

    private Date deadLine = new Date();

    //是否新建目的地址
    private boolean newDestAddress;

    private int destAddressId;

    @NotNull
    private Address destAddress;


    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public int getOrgAddressId() {
        return orgAddressId;
    }

    public void setOrgAddressId(int orgAddressId) {
        this.orgAddressId = orgAddressId;
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

    public Address getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(Address destAddress) {
        this.destAddress = destAddress;
    }

    public boolean isNewDestAddress() {
        return newDestAddress;
    }

    public void setNewDestAddress(boolean newDestAddress) {
        this.newDestAddress = newDestAddress;
    }

    public int getDestAddressId() {
        return destAddressId;
    }

    public void setDestAddressId(int destAddressId) {
        this.destAddressId = destAddressId;
    }
}
