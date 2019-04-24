package me.own.learn.sync.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/24 15:12
 */
@Document(indexName = "area_bn", type = "business")
public class BusinessKey implements Serializable {
    @Id
    @Field(store = true, index = FieldIndex.no, type = FieldType.String)
    private String businessCode;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String cityCode;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String businessName;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
