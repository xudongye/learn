package me.own.learn.mall.market.vo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:24
 */
public class MarketHomeContent {
    //轮播广告
    private List<HomeAdvertiseVo> advertiseList;
    //推荐品牌
    private List<MarketBrandInfo> brandList;
    //当前秒杀场次
    private MarketFlashInfo homeFlashPromotion;
    //新品推荐
    private List<MarketProductInfo> newProductList;
    //人气推荐
    private List<MarketProductInfo> hotProductList;
    //推荐专题
    private List<MarketSubjectInfo> subjectList;

    public List<HomeAdvertiseVo> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<HomeAdvertiseVo> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public List<MarketBrandInfo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<MarketBrandInfo> brandList) {
        this.brandList = brandList;
    }

    public MarketFlashInfo getHomeFlashPromotion() {
        return homeFlashPromotion;
    }

    public void setHomeFlashPromotion(MarketFlashInfo homeFlashPromotion) {
        this.homeFlashPromotion = homeFlashPromotion;
    }

    public List<MarketProductInfo> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<MarketProductInfo> newProductList) {
        this.newProductList = newProductList;
    }

    public List<MarketProductInfo> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<MarketProductInfo> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<MarketSubjectInfo> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<MarketSubjectInfo> subjectList) {
        this.subjectList = subjectList;
    }
}
