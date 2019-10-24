package me.own.learn.store.shoppingcart.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.store.shoppingcart.dto.ShoppingCartDto;
import me.own.learn.store.shoppingcart.vo.ShoppingCartVo;


import java.util.List;

public interface ShoppingCartService {

    //添加购物车
    ShoppingCartVo create(ShoppingCartDto shoppingCartDto);

    //修改购物车
    ShoppingCartVo update(ShoppingCartDto shoppingCartDto);

    //删除购物车
    void batchDelete(long customerId, List<Long> ids);

    PageQueryResult<ShoppingCartVo> page(int pageNum, int pageSize, long customerId, List<Long> ids);

    /***
     * Calculate all shopping cart items' price
     * @param customerId
     * @param ids shopping cart item id list
     * @return
     */
    double getAllShoppingCartItemPrice(long customerId, List<Long> ids);

    /***
     * Get customer's shopping cart items' id list
     * @param customerId
     * @return
     */
    List<Long> getShoppingCartItemIdsByCustomerId(long customerId);

    /***
     * Update customer's shopping cart item's quantity
     * @param customerId
     * @param id shopping cart item id
     * @param quantity
     * @return
     */
    ShoppingCartVo updateQuantity(long customerId, long id, int quantity);

    /***
     * Get shopping cart item by product id
     * @param customerId
     * @param productId
     * @return
     */
    ShoppingCartVo getShoppingCartItemByProductId(long customerId, long productId);
}
