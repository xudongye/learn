package me.own.learn.order.po;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "learn_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "OrderKeyGenerator")
    @GenericGenerator(name = "OrderKeyGenerator", strategy = "me.own.learn.order.po.OrderKeyGenerator")
    @Column(length = 150)
    private String orderNo;
    private Double totalPrice;
    private Long customerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;
    //地址编号
    private Long addressNo;
    //快递公司编号
    private String logisticsCom;
    //快递单号
    private String logisticsNo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date logisticsDate;
    private Date createTime;
    private Date modifyTime;
    private Boolean deleted;
    private Integer status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Long getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(Long addressNo) {
        this.addressNo = addressNo;
    }

    public String getLogisticsCom() {
        return logisticsCom;
    }

    public void setLogisticsCom(String logisticsCom) {
        this.logisticsCom = logisticsCom;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Date getLogisticsDate() {
        return logisticsDate;
    }

    public void setLogisticsDate(Date logisticsDate) {
        this.logisticsDate = logisticsDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
