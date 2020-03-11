package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeRecommendProductVo;
import me.own.learn.mall.market.vo.MarketProductInfo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:42
 */
public interface HomeRecommendProductService {

    PageQueryResult<HomeRecommendProductVo> page(int pageNum, int pageSize, String productName, Integer recommendStatus);

    List<MarketProductInfo> listHotProduct();
}
