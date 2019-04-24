package me.own.learn.sync.bo.responseBo;

import me.own.learn.sync.bo.BusinessKeyBo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/24 15:43
 */
public class ResponseBusinessBo {
    private List<BusinessKeyBo> businesses;

    public List<BusinessKeyBo> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BusinessKeyBo> businesses) {
        this.businesses = businesses;
    }
}
