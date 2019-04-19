package me.own.learn.sync.po;


import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/19 17:38
 */
public class City implements Serializable {
    private String cityCode;
    private String cityName;

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
