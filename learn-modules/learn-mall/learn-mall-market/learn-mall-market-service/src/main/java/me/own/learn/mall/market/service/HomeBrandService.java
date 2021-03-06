package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeBrandVo;
import me.own.learn.mall.market.vo.MarketBrandInfo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:29
 */
public interface HomeBrandService {

    PageQueryResult<HomeBrandVo> page(int pageNum, int pageSize, String brandName, Integer recommendStatus);

    List<MarketBrandInfo> listRecommendBrand();
}
