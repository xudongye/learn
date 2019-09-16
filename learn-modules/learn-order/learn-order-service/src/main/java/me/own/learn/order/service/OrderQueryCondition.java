package me.own.learn.order.service;

public class OrderQueryCondition {
    private String orderNo;
    private Long customerId;
    private Integer paymentType;
    //地址编号
    private Long addressNo;
    //快递公司编号
    private String logisticsCom;
    //快递单号
    private String logisticsNo;

    private Long createTimeFrom;
    private Long createTimeTo;
    private Long payDateFrom;
    private Long payDateTo;
    private Integer status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
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

    public Long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public Long getPayDateFrom() {
        return payDateFrom;
    }

    public void setPayDateFrom(Long payDateFrom) {
        this.payDateFrom = payDateFrom;
    }

    public Long getPayDateTo() {
        return payDateTo;
    }

    public void setPayDateTo(Long payDateTo) {
        this.payDateTo = payDateTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
