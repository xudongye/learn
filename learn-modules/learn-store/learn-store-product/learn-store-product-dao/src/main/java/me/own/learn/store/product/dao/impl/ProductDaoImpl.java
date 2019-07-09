package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.po.Product;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 17:04
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public void putSold(List<Long> productIds, int status) {
        Query query = getCurrentSession().createQuery("update Product set status = :status, modifyTime = :modifyTime where id in :productIds");
        query.setParameterList("productIds", productIds);
        query.setParameter("status", status);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }
}
