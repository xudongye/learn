package me.own.learn.sync.bo;

import java.util.List;

/**
 * @author:simonye
 * @date 19:37 2019/4/20
 **/
public class ProvinceBo {
    private String provinceCode;
    private String provinceName;
    private List<CityBo> citys;

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

    public List<CityBo> getCitys() {
        return citys;
    }

    public void setCitys(List<CityBo> citys) {
        this.citys = citys;
    }
}
