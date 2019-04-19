package me.own.learn.sync.bo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 11:50
 */
public class CountryBo {
    private String countryCode;
    private String countryName;
    private List<CityBo> cities;

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

    public List<CityBo> getCities() {
        return cities;
    }

    public void setCities(List<CityBo> cities) {
        this.cities = cities;
    }
}
