package me.own.learn.store.product.controller.cmd;

import java.util.List;

public class ProductPropertyCmd {

    private Long productId;

    private List<Long> propertyIds;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getPropertyIds() {
        return propertyIds;
    }

    public void setPropertyIds(List<Long> propertyIds) {
        this.propertyIds = propertyIds;
    }
}
