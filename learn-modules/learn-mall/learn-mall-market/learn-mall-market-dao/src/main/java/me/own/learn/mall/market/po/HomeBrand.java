package me.own.learn.mall.market.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:26
 */
@Entity
@Table(name = "home_brand")
public class HomeBrand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long brandId;

    private String brandName;

    private Integer recommendStatus;

    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
