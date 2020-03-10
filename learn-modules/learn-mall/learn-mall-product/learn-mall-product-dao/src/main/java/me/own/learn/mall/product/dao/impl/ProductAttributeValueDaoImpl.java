package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductAttributeValueDao;
import me.own.learn.mall.product.po.ProductAttributeValue;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 16:12
 */
@Repository
public class ProductAttributeValueDaoImpl extends BaseDaoImpl<ProductAttributeValue> implements ProductAttributeValueDao {
    @Override
    protected Class<ProductAttributeValue> getEntityClass() {
        return ProductAttributeValue.class;
    }
}
