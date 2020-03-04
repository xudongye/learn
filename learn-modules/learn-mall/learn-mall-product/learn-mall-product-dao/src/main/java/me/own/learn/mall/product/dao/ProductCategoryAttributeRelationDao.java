package me.own.learn.mall.product.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.product.po.ProductCategoryAttributeRelation;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 9:36
 */
public interface ProductCategoryAttributeRelationDao extends BaseDao<ProductCategoryAttributeRelation> {

    List<ProductCategoryAttributeRelation> getByProductCategoryId(long productCategoryId);
}
