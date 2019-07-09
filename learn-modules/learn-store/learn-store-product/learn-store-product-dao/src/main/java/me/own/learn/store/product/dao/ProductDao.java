package me.own.learn.store.product.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.store.product.po.Product;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 17:03
 */
public interface ProductDao extends BaseDao<Product> {


    void putSold(List<Long> productIds, int status);
}
