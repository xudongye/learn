package me.own.learn.event.service.message.customer;

import me.own.learn.event.service.MessageCarriable;

public class CustomerUnSubscribeMessage implements MessageCarriable {
    private String appId;
    private String customerOpenId;

    public CustomerUnSubscribeMessage(String appId, String customerOpenId) {
        this.appId = appId;
        this.customerOpenId = customerOpenId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCustomerOpenId() {
        return customerOpenId;
    }

    public void setCustomerOpenId(String customerOpenId) {
        this.customerOpenId = customerOpenId;
    }
}

