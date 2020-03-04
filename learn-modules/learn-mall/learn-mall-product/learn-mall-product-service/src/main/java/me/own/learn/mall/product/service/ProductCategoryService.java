package me.own.learn.mall.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.product.dto.ProductCategoryDto;
import me.own.learn.mall.product.vo.ProductAttributeCategoryItemVo;
import me.own.learn.mall.product.vo.ProductCategoryVo;
import me.own.learn.mall.product.vo.ProductCategoryWithChildrenItemVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 9:27
 */
public interface ProductCategoryService {

    ProductCategoryVo create(ProductCategoryDto dto);

    ProductCategoryVo updateById(long id, ProductCategoryDto dto);

    PageQueryResult<ProductCategoryVo> page(int pageNum, int pageSize, long parentId);

    ProductCategoryVo getById(long id);

    List<ProductCategoryWithChildrenItemVo> listWithChildren();
}
