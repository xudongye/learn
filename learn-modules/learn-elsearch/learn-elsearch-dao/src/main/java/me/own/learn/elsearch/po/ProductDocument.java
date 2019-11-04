package me.own.learn.elsearch.po;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "learn", type = "product")
public class ProductDocument {

    private Long id;
    private String productNo;//商品编号
    private String name;//商品名
    private String title;//标题
    private String description;//描述
    private String categoryName;//所属类目

    public ProductDocument() {
    }

    public ProductDocument(String productNo, String name, String title, String description, String categoryName) {
        this.productNo = productNo;
        this.name = name;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
