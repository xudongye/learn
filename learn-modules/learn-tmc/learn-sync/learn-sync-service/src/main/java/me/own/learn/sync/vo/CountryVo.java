package me.own.learn.sync.vo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 16:03
 */
public class CountryVo {
    private Long id;
    private String countryCode;
    private String countryName;
    private List<ProvinceVo> provinces;

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

    public List<ProvinceVo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceVo> provinces) {
        this.provinces = provinces;
    }
}
