package me.own.learn.mall.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.product.dto.NewProductDto;
import me.own.learn.mall.product.dto.ProductDto;
import me.own.learn.mall.product.vo.ProductVo;

/**
 * @author: yexudong
 * @Date: 2020/3/5 13:35
 */
public interface NewProductService {

    PageQueryResult<ProductVo> page(int pageNum, int pageSize, NewProductQueryCondition condition);

    ProductVo create(NewProductDto dto);
}
