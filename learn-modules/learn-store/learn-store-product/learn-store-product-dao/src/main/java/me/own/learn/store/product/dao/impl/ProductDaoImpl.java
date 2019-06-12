package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.po.Product;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/6/12 17:04
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}
