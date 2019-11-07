package me.own.learn.event.service.message.product;

import me.own.learn.event.service.MessageCarriable;

public class ProductDeleteMessage implements MessageCarriable {
    private String skuNo;

    public ProductDeleteMessage() {
    }

    public ProductDeleteMessage(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }
}
