package me.own.learn.file.bo;

/**
 * @author yexudong
 * @date 2019/6/14 10:26
 */
public class PictureFileUploadResultBo extends FileUploadResultBo {
    private Integer width;

    private Integer height;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
