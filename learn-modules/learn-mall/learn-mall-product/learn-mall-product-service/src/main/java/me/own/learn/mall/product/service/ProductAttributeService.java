package me.own.learn.mall.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.product.dto.ProductAttributeDto;
import me.own.learn.mall.product.vo.ProductAttrInfoVo;
import me.own.learn.mall.product.vo.ProductAttributeVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:32
 */
public interface ProductAttributeService {

    ProductAttributeVo create(ProductAttributeDto dto);

    PageQueryResult<ProductAttributeVo> page(long cid, int pageNum, int pageSize, int type);

    List<ProductAttrInfoVo> getProductAttrInfos(long productCategoryId);
}
