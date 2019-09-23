package me.own.learn.shoppingcar.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.shoppingcar.dto.ShoppingCarDto;
import me.own.learn.shoppingcar.vo.ShoppingCarVo;

import java.util.List;

public interface ShoppingCarService {

    //添加购物车
    ShoppingCarVo create(ShoppingCarDto shoppingCarDto);

    //修改购物车
    ShoppingCarVo update(ShoppingCarDto shoppingCarDto);

    //删除购物车
    void batchDelete(List<Long> ids);

    PageQueryResult<ShoppingCarVo> page(int pageNum, int pageSize, long customerId);
}
