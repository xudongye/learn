package me.own.learn.sync.po;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;


/**
 * @author yexudong
 * @date 2019/4/19 17:38
 */
@Document(indexName = "city", type = "ct", shards = 1,
        replicas = 0, refreshInterval = "-1")
public class City implements Serializable {

    @Id
    private Long id;
    private String cityCode;
    private String cityName;
    @Field(type = FieldType.Nested)
    private List<Business> businesses;
    @Field(type = FieldType.Nested)
    private List<District> districts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
