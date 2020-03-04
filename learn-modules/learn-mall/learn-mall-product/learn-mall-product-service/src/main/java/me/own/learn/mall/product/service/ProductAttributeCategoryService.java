package me.own.learn.mall.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.product.vo.ProductAttributeCategoryItemVo;
import me.own.learn.mall.product.vo.ProductAttributeCategoryVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:10
 */
public interface ProductAttributeCategoryService {

    ProductAttributeCategoryVo create(String name);

    PageQueryResult<ProductAttributeCategoryVo> page(int pageNum, int pageSize);

    /**
     * 获取所有商品属性分类及其下属性
     *
     * @return
     */
    List<ProductAttributeCategoryItemVo> withAttr();
}
