package me.own.learn.mall.market.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:30
 */
public class MarketBrandInfo {

    private Long id;

    private String name;

    @ApiModelProperty(value = "首字母")
    private String firstLetter;
    private Integer sort;
    @ApiModelProperty(value = "品牌logo")
    private String logo;
    @ApiModelProperty(value = "专区大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBrandStory() {
        return brandStory;
    }

    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }
}
