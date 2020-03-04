package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductAttributeDao;
import me.own.learn.mall.product.po.ProductAttribute;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:31
 */
@Repository
public class ProductAttributeDaoImpl extends BaseDaoImpl<ProductAttribute> implements ProductAttributeDao {
    @Override
    protected Class<ProductAttribute> getEntityClass() {
        return ProductAttribute.class;
    }
}
