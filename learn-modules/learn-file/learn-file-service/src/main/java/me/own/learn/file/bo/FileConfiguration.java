package me.own.learn.file.bo;

/**
 * @author yexudong
 * @date 2019/6/14 10:40
 */
public class FileConfiguration {
    private String imageActionName;
    private String imageFieldName;
    private Long imageMaxSize;
    private String imageUrlPrefix;

    public String getImageActionName() {
        return imageActionName;
    }

    public void setImageActionName(String imageActionName) {
        this.imageActionName = imageActionName;
    }

    public String getImageFieldName() {
        return imageFieldName;
    }

    public void setImageFieldName(String imageFieldName) {
        this.imageFieldName = imageFieldName;
    }

    public Long getImageMaxSize() {
        return imageMaxSize;
    }

    public void setImageMaxSize(Long imageMaxSize) {
        this.imageMaxSize = imageMaxSize;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public void setImageUrlPrefix(String imageUrlPrefix) {
        this.imageUrlPrefix = imageUrlPrefix;
    }

}
