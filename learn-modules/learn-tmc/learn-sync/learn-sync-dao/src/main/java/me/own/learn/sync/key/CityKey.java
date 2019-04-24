package me.own.learn.sync.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/24 11:57
 */
@Document(indexName = "area_ct", type = "city")
public class CityKey implements Serializable {

    @Id
    @Field(store = true, index = FieldIndex.no, type = FieldType.String)
    private String cityCode;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String countryName;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String provinceName;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String cityName;


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
