package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductAttributeCategoryDao;
import me.own.learn.mall.product.po.ProductAttributeCategory;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:09
 */
@Repository
public class ProductAttributeCategoryDaoImpl extends BaseDaoImpl<ProductAttributeCategory> implements ProductAttributeCategoryDao {
    @Override
    protected Class<ProductAttributeCategory> getEntityClass() {
        return ProductAttributeCategory.class;
    }
}
