package me.own.learn.sync.key;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/24 15:07
 */
@Document(indexName = "area_dt", type = "district")
public class DistrictKey implements Serializable {

    @Id
    @Field(store = true, index = FieldIndex.no, type = FieldType.String)
    private String districtCode;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String cityCode;
    @Field(index = FieldIndex.analyzed, store = true, type = FieldType.String)
    private String districtName;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
