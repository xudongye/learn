package me.own.learn.image.dto;


/**
 * @author yexudong
 * @date 2019/7/3 14:02
 */
public class ImageDto extends ImageBaseDto {

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
