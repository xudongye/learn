package me.own.learn.event.service.message.customer;

import me.own.learn.event.service.MessageCarriable;

public class CustomerScanQRCodeMessage implements MessageCarriable {
    private String appId;
    private Long customerId;
    private String customerOpenId;
    private String eventKey;

    public CustomerScanQRCodeMessage(Long customerId, String eventKey) {
        this.customerId = customerId;
        this.eventKey = eventKey;
    }

    public CustomerScanQRCodeMessage(String appId, String customerOpenId, String eventKey) {
        this.appId = appId;
        this.customerOpenId = customerOpenId;
        this.eventKey = eventKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerOpenId() {
        return customerOpenId;
    }

    public void setCustomerOpenId(String customerOpenId) {
        this.customerOpenId = customerOpenId;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
