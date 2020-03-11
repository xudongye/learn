package me.own.learn.mall.market.vo;

/**
 * @author: yexudong
 * @Date: 2020/3/11 11:27
 */
public class HomeFlashProductVo extends HomeFlashPromotionProductRelationVo {

    private MarketProductInfo product;

    public MarketProductInfo getProduct() {
        return product;
    }

    public void setProduct(MarketProductInfo product) {
        this.product = product;
    }
}
