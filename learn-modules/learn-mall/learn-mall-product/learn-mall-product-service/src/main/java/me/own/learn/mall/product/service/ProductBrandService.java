package me.own.learn.mall.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.product.dto.ProductBrandDto;
import me.own.learn.mall.product.vo.ProductBrandVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/3 11:33
 */
public interface ProductBrandService {

    ProductBrandVo create(ProductBrandDto dto);

    ProductBrandVo updateById(long id, ProductBrandDto dto);

    PageQueryResult<ProductBrandVo> page(int pageNum, int pageSize, String brandName);

    ProductBrandVo getById(long id);
}
