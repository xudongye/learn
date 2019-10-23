package me.own.learn.shoppingcar.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.shoppingcar.dao.ShoppingCartDao;
import me.own.learn.shoppingcar.po.ShoppingCart;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartDaoImpl extends BaseDaoImpl<ShoppingCart> implements ShoppingCartDao {
    @Override
    protected Class<ShoppingCart> getEntityClass() {
        return ShoppingCart.class;
    }

    @Override
    public ShoppingCart getByProductId(long customerId, long productId) {
        Criteria criteria = getCurrentSession().createCriteria(ShoppingCart.class);
        criteria.add(Restrictions.eq("deleted", false));
        criteria.add(Restrictions.eq("customerId", customerId));
        criteria.add(Restrictions.eq("productId", productId));
        return (ShoppingCart) criteria.uniqueResult();
    }

    @Override
    public double getAllShoppingCartItemPrice(long customerId, List<Long> ids) {
        String hql = "select sum((item.quantity * item.sourceUnitPrice)) from ShoppingCart item where customerId = :customerId and item.deleted = false";
        if (ids != null && ids.size() > 0) {
            hql = hql + " and id in :ids";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("customerId", customerId);
        if (ids != null && ids.size() > 0) {
            query.setParameterList("ids", ids);
        }
        return query.uniqueResult() == null ? 0 : ((Double)query.uniqueResult()).doubleValue();
    }

    @Override
    public List<Long> getCustomerShoppingCartItemIds(long customerId) {
        String hql = "select item.id from ShoppingCart item where customerId = :customerId and item.deleted = false";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("customerId", customerId);
        return query.list();
    }

    @Override
    public void batchDeleteShoppingCartItemByIds(Long customerId, List<Long> ids) {
        String hql = "update ShoppingCart item set item.deleted = true where item.customerId = :customerId and item.id in :ids";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("customerId", customerId);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }
}
