package me.own.learn.store.product.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.store.product.po.ProductPropertyItem;

public interface ProductPropertyItemDao extends BaseDao<ProductPropertyItem> {

    void setValue(long productId, long propertyId, String value);

    ProductPropertyItem getByProductIdAndPropertyId(long productId, long propertyId);
}
