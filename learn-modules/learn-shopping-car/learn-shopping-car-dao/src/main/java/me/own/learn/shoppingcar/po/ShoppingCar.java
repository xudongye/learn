package me.own.learn.shoppingcar.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "learn_shopping")
public class ShoppingCar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long productId;

    private Integer quantity;

    //添加到购物车初始单价
    private Double sourceUnitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSourceUnitPrice() {
        return sourceUnitPrice;
    }

    public void setSourceUnitPrice(Double sourceUnitPrice) {
        this.sourceUnitPrice = sourceUnitPrice;
    }
}
