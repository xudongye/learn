package me.own.learn.sync.vo;


import java.util.List;

/**
 * @author:simonye
 * @date 22:26 2019/4/20
 **/
public class ProvinceVo {
    private Long id;
    private String provinceCode;
    private String provinceName;
    private List<CityVo> citys;

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

    public List<CityVo> getCitys() {
        return citys;
    }

    public void setCitys(List<CityVo> citys) {
        this.citys = citys;
    }
}
