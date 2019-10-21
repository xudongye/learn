package me.own.learn.order.vo;

import java.util.Date;

public class OrderVo {
    private String orderNo;
    private Double totalPrice;
    //减去 积分抵扣 或 优惠 金额
    private Double subtractValue;
    private Long customerId;
    private Date payDate;
    //地址编号
    private Long addressNo;
    //快递公司编号
    private String logisticsCom;
    //快递单号
    private String logisticsNo;
    private Date createTime;
    private Date modifyTime;
    private Boolean deleted;
    private Integer status;
    //是否为默认收货地址
    private Boolean beDefault;

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

    public Double getSubtractValue() {
        return subtractValue;
    }

    public void setSubtractValue(Double subtractValue) {
        this.subtractValue = subtractValue;
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

    public Boolean getBeDefault() {
        return beDefault;
    }

    public void setBeDefault(Boolean beDefault) {
        this.beDefault = beDefault;
    }
}
