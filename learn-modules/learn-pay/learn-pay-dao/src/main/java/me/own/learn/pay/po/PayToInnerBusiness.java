package me.own.learn.pay.po;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PayToInnerBusiness implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAY_ORDER_ID")
    private PayOrder payOrder;

    /***
     * Business id could be order id, crowd funding support id or reward id
     *
     * determined by the business type
     */
    private String businessId;

    /***
     * payment business type
     *
     * -- 1 represents order
     * -- 2 represents crowd
     * -- 3 represents reward
     */
    private int businessType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayOrder getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(PayOrder payOrder) {
        this.payOrder = payOrder;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }
}
