package me.own.learn.store.product.dto;

/**
 * @author: yexudong
 * @Date: 2020/2/28 11:40
 */
public class ProductImageDto {
    private Boolean isMain;
    private String url;

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
