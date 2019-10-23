package me.own.learn.shoppingcar.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.shoppingcar.po.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao extends BaseDao<ShoppingCart> {

    ShoppingCart getByProductId(long customerId, long productId);

    /***
     *
     * @param customerId
     * @param ids
     * @return
     */
    double getAllShoppingCartItemPrice(long customerId, List<Long> ids);

    /***
     * Get customer's all available shopping item ids
     * @param customerId
     * @return
     */
    List<Long> getCustomerShoppingCartItemIds(long customerId);

    /***
     * Batch delete shopping items
     * @param customerId
     * @param ids
     */
    void batchDeleteShoppingCartItemByIds(Long customerId, List<Long> ids);
}
