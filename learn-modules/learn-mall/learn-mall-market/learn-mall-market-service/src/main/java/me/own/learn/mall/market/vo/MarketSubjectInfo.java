package me.own.learn.mall.market.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:29
 */
public class MarketSubjectInfo {
    private Long id;
    private String title;
    @ApiModelProperty(value = "专题主图")
    private String pic;
    private String content;
    private Integer readCount;
    @ApiModelProperty(value = "转发数")
    private Integer forwardCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }
}
