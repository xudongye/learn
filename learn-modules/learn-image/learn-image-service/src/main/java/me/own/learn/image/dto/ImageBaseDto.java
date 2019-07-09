package me.own.learn.image.dto;

import me.own.learn.image.constant.ImageConstant;

/**
 * @author yexudong
 * @date 2019/7/3 14:02
 */
public class ImageBaseDto {
    private Long id;

    private String url;

    private ImageConstant.ImageType imageType;

    private Boolean isMain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
