package me.own.learn.image.dto;


/**
 * @author yexudong
 * @date 2019/7/3 14:02
 */
public class ImageDto extends ImageBaseDto {

    private Long productId;
    private Long customerId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
