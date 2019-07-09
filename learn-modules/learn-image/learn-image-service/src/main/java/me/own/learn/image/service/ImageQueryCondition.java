package me.own.learn.image.service;


import me.own.learn.image.constant.ImageConstant;

/**
 * @author yexudong
 * @date 2019/7/3 14:35
 */
public class ImageQueryCondition {

    private String url;

    private ImageConstant.ImageType imageType;

    private Boolean isMain;

    private Long createTimeFrom;
    private Long createTimeTo;

    private Long productId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageConstant.ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageConstant.ImageType imageType) {
        this.imageType = imageType;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public Long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
