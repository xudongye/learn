package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeRecommendNewProductVo;
import me.own.learn.mall.market.vo.MarketProductInfo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:10
 */
public interface HomeRecommendNewProductService {

    PageQueryResult<HomeRecommendNewProductVo> page(int pageNum, int pageSize, String productName, Integer recommendStatus);

    List<MarketProductInfo> listRecommendProduct();
}
