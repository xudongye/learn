package me.own.learn.sync.po;

import java.io.Serializable;

/**
 * @author:simonye
 * @date 1:01 2019/4/20
 **/
public class Business implements Serializable {

    private String businessCode;
    private String businessName;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
