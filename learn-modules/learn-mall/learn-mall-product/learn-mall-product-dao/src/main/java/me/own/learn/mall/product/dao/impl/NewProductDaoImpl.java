package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.NewProductDao;
import me.own.learn.mall.product.po.NewProduct;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/5 13:34
 */
@Repository
public class NewProductDaoImpl extends BaseDaoImpl<NewProduct> implements NewProductDao {
    @Override
    protected Class<NewProduct> getEntityClass() {
        return NewProduct.class;
    }
}
