package me.own.learn.sync.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 13:02
 */
@Document(indexName = "country", type = "cn", shards = 1,
        replicas = 0, refreshInterval = "-1")
public class Country implements Serializable {

    @Id
    private Long id;
    private String countryCode;
    private String countryName;
    @Field(type = FieldType.Nested)
    private List<Province> provinces;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
