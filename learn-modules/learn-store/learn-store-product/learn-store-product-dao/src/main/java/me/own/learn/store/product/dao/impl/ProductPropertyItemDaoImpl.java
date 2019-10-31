package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.ProductPropertyItemDao;
import me.own.learn.store.product.po.ProductPropertyItem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPropertyItemDaoImpl extends BaseDaoImpl<ProductPropertyItem> implements ProductPropertyItemDao {
    @Override
    protected Class<ProductPropertyItem> getEntityClass() {
        return ProductPropertyItem.class;
    }

    @Override
    public void setValue(long productId, long propertyId, String value) {
        Query query = getCurrentSession().createQuery(
                "update ProductPropertyItem set propertyValue = :propertyValue where product.id = :productId and propertyItem.id = :propertyId");
        query.setParameter("propertyValue", value);
        query.setParameter("productId", productId);
        query.setParameter("propertyId", propertyId);
        query.executeUpdate();
    }

    @Override
    public ProductPropertyItem getByProductIdAndPropertyId(long productId, long propertyId) {
        Criteria criteria = getCurrentSession().createCriteria(ProductPropertyItem.class);
        criteria.add(Restrictions.eq("product.id", productId));
        criteria.add(Restrictions.eq("propertyItem.id", propertyId));
        return (ProductPropertyItem) criteria.uniqueResult();
    }
}
