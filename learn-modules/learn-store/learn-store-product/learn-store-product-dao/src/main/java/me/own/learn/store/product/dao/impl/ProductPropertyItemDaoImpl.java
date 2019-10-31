package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.ProductPropertyItemDao;
import me.own.learn.store.product.po.ProductPropertyItem;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPropertyItemDaoImpl extends BaseDaoImpl<ProductPropertyItem> implements ProductPropertyItemDao {
    @Override
    protected Class<ProductPropertyItem> getEntityClass() {
        return ProductPropertyItem.class;
    }
}
