package me.own.learn.store.product.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.store.product.dto.ProductDto;
import me.own.learn.store.product.vo.ProductDetailVo;
import me.own.learn.store.product.vo.ProductVo;

import java.util.List;
import java.util.Set;

/**
 * @author yexudong
 * @date 2019/6/12 18:21
 */
public interface ProductService {

    ProductVo create(ProductDto productDto);

    ProductVo update(ProductDto productDto);

    ProductVo getById(long productId);

    //获取产品详细  包含类目
    ProductDetailVo getByProductId(long productId);

    //商品下架
    void soldOut(long productId);

    //批量下下架
    void putSold(List<Long> productIds, int status);

    void delete(long productId);

    //清库存
    void reduceInventory(long productId, int count);

    PageQueryResult<ProductVo> page(int pageNum, int pageSize, ProductQueryCondition condition);
}
