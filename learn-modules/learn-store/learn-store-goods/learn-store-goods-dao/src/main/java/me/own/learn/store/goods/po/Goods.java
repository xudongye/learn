package me.own.learn.store.goods.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "learn_store_goods")
public class Goods extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PRODUCT_ID")
    private Long productId;

    private String name;

    private double price;

    private int inventory;

    private String status;

    private String color;

    private String size;


}
