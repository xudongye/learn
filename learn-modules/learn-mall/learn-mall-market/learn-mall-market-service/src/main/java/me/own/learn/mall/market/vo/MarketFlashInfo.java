package me.own.learn.mall.market.vo;

import java.util.Date;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:31
 */
public class MarketFlashInfo {
    private Date startTime;
    private Date endTime;
    private Date nextStartTime;
    private Date nextEndTime;
    private List<MarketProductInfo> productList;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(Date nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public Date getNextEndTime() {
        return nextEndTime;
    }

    public void setNextEndTime(Date nextEndTime) {
        this.nextEndTime = nextEndTime;
    }

    public List<MarketProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<MarketProductInfo> productList) {
        this.productList = productList;
    }
}
