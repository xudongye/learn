package me.own.learn.sync.po;

import java.io.Serializable;

/**
 * @author:simonye
 * @date 1:01 2019/4/20
 **/
public class District implements Serializable {
    private String districtCode;
    private String districtName;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
