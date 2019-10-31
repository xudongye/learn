package me.own.learn.store.goods.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 多对多关系的中间表，但是由于想加上一个携带值，所以不能使用PO自动维护
 */
@Entity
@Table(name = "GOODS_CARRY")
public class GoodsCarry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Goods goods;
    //carry param
    private String carryParam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getCarryParam() {
        return carryParam;
    }

    public void setCarryParam(String carryParam) {
        this.carryParam = carryParam;
    }
}
