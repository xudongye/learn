package me.own.learn.sync.vo;

/**
 * @author:simonye
 * @date 17:51 2019/4/20
 **/
public class CityVo {
    private Long id;
    private String cityCode;
    private String cityName;

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
}
