package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductCategoryAttributeRelationDao;
import me.own.learn.mall.product.po.ProductCategoryAttributeRelation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 9:36
 */
@Repository
public class ProductCategoryAttributeRelationDaoImpl
        extends BaseDaoImpl<ProductCategoryAttributeRelation> implements ProductCategoryAttributeRelationDao {
    @Override
    protected Class<ProductCategoryAttributeRelation> getEntityClass() {
        return ProductCategoryAttributeRelation.class;
    }

    @Override
    public List<ProductCategoryAttributeRelation> getByProductCategoryId(long productCategoryId) {
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryAttributeRelation.class);
        criteria.add(Restrictions.eq("productCategoryId", productCategoryId));
        return criteria.list();
    }
}
