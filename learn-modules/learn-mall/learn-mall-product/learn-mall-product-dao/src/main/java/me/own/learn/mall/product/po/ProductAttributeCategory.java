package me.own.learn.mall.product.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:07
 */
@Entity
@Table(name = "mall_product_attr_cate")
public class ProductAttributeCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer attributeCount;

    private Integer paramCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(Integer attributeCount) {
        this.attributeCount = attributeCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }
}
