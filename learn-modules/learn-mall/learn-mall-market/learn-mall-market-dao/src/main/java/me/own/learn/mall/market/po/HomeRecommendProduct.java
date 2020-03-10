package me.own.learn.mall.market.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:40
 */
@Entity
@Table(name = "home_recommend_product")
public class HomeRecommendProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
