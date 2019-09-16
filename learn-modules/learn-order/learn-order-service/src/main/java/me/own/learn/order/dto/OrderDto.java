package me.own.learn.order.dto;


public class OrderDto {
    private String orderNo;
    private Double totalPrice;
    private Long customerId;
    //地址编号
    private Long addressNo;
    //快递公司编号
    private String logisticsCom;
    //快递单号
    private String logisticsNo;
    //收货地址
    private OrderAddressDto address;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public OrderAddressDto getAddress() {
        return address;
    }

    public void setAddress(OrderAddressDto address) {
        this.address = address;
    }

    public Boolean getBeDefault() {
        return beDefault;
    }

    public void setBeDefault(Boolean beDefault) {
        this.beDefault = beDefault;
    }
}
