package me.own.learn.sync.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * @author:simonye
 * @date 21:59 2019/4/20
 **/
@Document(indexName = "province", type = "pv", shards = 1,
        replicas = 0, refreshInterval = "-1")
public class Province implements Serializable {

    @Id
    private Long id;
    private String provinceCode;
    private String provinceName;
    @Field(type = FieldType.Nested)
    private List<City> citys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }
}
