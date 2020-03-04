package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.ProductCategoryDao;
import me.own.learn.mall.product.po.ProductCategory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 9:26
 */
@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory> implements ProductCategoryDao {
    @Override
    protected Class<ProductCategory> getEntityClass() {
        return ProductCategory.class;
    }

    @Override
    public List<ProductCategory> listByParentId(long parentId) {
        Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class);
        criteria.add(Restrictions.eq("parentId", parentId));
        return criteria.list();
    }
}
