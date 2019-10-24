package me.own.learn.store.product.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 产品携带属性表，品牌，颜色，重量，单位，库存，内存...等
 */
@Entity
@Table(name = "learn_store_carry")
public class Carry extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carryName;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "carries")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarryName() {
        return carryName;
    }

    public void setCarryName(String carryName) {
        this.carryName = carryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
