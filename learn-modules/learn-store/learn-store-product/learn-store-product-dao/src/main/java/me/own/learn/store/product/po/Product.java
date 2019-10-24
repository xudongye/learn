package me.own.learn.store.product.po;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 16:28
 */
@Entity
@Table(name = "learn_store_product")
//@org.hibernate.annotations.Cache(region = "product", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //产品名称：手机，电脑
    private String name;
    private String icon;
    private Boolean deleted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCT_CARRY", joinColumns = {@JoinColumn(name = "PRODUCT_ID")}, inverseJoinColumns = {@JoinColumn(name = "CARRY_ID")})
    private List<Carry> carries;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<Carry> getCarries() {
        return carries;
    }

    public void setCarries(List<Carry> carries) {
        this.carries = carries;
    }
}
