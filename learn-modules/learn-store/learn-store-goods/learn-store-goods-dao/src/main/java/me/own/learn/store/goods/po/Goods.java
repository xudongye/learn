package me.own.learn.store.goods.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "learn_store_goods")
public class Goods extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PRODUCT_ID")//所属产品：手机，电脑
    private Long productId;
    private String name;
    //品牌：华为，小米
    private String brand;
    //单价
    private double unitPrice;
    //库存
    private Integer inventory;
    //标题
    private String title;
    @Lob
    private String description;
    //商品状态，1-正常，2-下架
    private Integer status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date soldOutTime;
    //点击量
    private Long hitCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSoldOutTime() {
        return soldOutTime;
    }

    public void setSoldOutTime(Date soldOutTime) {
        this.soldOutTime = soldOutTime;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }
}
