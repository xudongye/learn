package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductBrandDao;
import me.own.learn.mall.product.po.ProductBrand;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/3 11:27
 */
@Repository
public class ProductBrandImpl extends BaseDaoImpl<ProductBrand> implements ProductBrandDao {
    @Override
    protected Class<ProductBrand> getEntityClass() {
        return ProductBrand.class;
    }
}
