package me.own.learn.store.product.dto;

public class ProductPropertyDto {
    private Long propertyId;
    private String value;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
