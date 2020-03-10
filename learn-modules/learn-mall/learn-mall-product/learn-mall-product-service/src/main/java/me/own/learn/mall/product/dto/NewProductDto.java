package me.own.learn.mall.product.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/8 19:56
 */
public class NewProductDto extends ProductDto {
    @ApiModelProperty("商品阶梯价格设置")
    private List<ProductLadderDto> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<ProductFullReductionDto> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<ProductMemberPriceDto> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<ProductSkuStockDto> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<ProductAttributeValueDto> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<SubjectProductRelationDto> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<PreferenceAreaProductRelationDto> prefrenceAreaProductRelationList;

    public List<ProductLadderDto> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<ProductLadderDto> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<ProductFullReductionDto> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<ProductFullReductionDto> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List<ProductMemberPriceDto> getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List<ProductMemberPriceDto> memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List<ProductSkuStockDto> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<ProductSkuStockDto> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<ProductAttributeValueDto> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<ProductAttributeValueDto> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public List<SubjectProductRelationDto> getSubjectProductRelationList() {
        return subjectProductRelationList;
    }

    public void setSubjectProductRelationList(List<SubjectProductRelationDto> subjectProductRelationList) {
        this.subjectProductRelationList = subjectProductRelationList;
    }

    public List<PreferenceAreaProductRelationDto> getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }

    public void setPrefrenceAreaProductRelationList(List<PreferenceAreaProductRelationDto> prefrenceAreaProductRelationList) {
        this.prefrenceAreaProductRelationList = prefrenceAreaProductRelationList;
    }
}
