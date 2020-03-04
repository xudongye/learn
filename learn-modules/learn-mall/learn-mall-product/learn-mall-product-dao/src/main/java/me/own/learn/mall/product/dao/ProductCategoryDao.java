package me.own.learn.mall.product.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.product.po.ProductCategory;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 9:26
 */
public interface ProductCategoryDao extends BaseDao<ProductCategory> {

    List<ProductCategory> listByParentId(long parentId);
}
