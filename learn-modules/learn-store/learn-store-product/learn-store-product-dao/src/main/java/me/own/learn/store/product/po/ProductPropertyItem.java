package me.own.learn.store.product.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Product/Property多对多关系的中间表，但是由于想加上产品属性值,所以不能使用PO自动维护
 */
@Entity
@Table(name = "product_property")
public class ProductPropertyItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PropertyItem propertyItem;

    @ManyToOne
    private Product product;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean enable;

    private String propertyValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyItem getPropertyItem() {
        return propertyItem;
    }

    public void setPropertyItem(PropertyItem propertyItem) {
        this.propertyItem = propertyItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
