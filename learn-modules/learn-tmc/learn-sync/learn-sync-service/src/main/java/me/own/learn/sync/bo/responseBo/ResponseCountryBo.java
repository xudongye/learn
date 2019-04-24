package me.own.learn.sync.bo.responseBo;

import me.own.learn.sync.bo.CountryBo;

import java.util.List;

/**
 * @author:simonye
 * @date 16:23 2019/4/21
 **/
public class ResponseCountryBo {
    private List<CountryBo> countries;

    public ResponseCountryBo() {
    }

    public ResponseCountryBo(List<CountryBo> countries) {
        this.countries = countries;
    }
    public List<CountryBo> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryBo> countries) {
        this.countries = countries;
    }
}
