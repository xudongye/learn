package me.own.learn.mall.market.service.impl;

import me.own.learn.mall.market.service.*;
import me.own.learn.mall.market.vo.MarketHomeContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:35
 */
@Service
public class MarketHomeContentServiceImpl implements MarketHomeContentService {

    @Autowired
    private HomeAdvertiseService advertiseService;
    @Autowired
    private HomeBrandService homeBrandService;
    @Autowired
    private HomeFlashPromotionService promotionService;
    @Autowired
    private HomeRecommendNewProductService newProductService;
    @Autowired
    private HomeRecommendProductService hotProductService;
    @Autowired
    private HomeRecommendSubjectService recommendSubjectService;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MarketHomeContent getHomeContent() {
        MarketHomeContent content = new MarketHomeContent();
        //获取首页广告
        content.setAdvertiseList(advertiseService.listValid());
        //品牌推荐
        content.setBrandList(homeBrandService.listRecommendBrand());
        //获取秒杀信息
        content.setHomeFlashPromotion(promotionService.getHomePromotion());
        //获取新品推荐
        content.setNewProductList(newProductService.listRecommendProduct());
        //获取人气推荐
        content.setHotProductList(hotProductService.listHotProduct());
        //获取推荐专题
        content.setSubjectList(recommendSubjectService.listRecommendSubject());
        return content;
    }
}
