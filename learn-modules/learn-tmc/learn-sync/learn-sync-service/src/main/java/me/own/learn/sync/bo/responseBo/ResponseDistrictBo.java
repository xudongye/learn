package me.own.learn.sync.bo.responseBo;

import me.own.learn.sync.bo.DistrictKeyBo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/24 15:36
 */
public class ResponseDistrictBo {
    private List<DistrictKeyBo> districts;

    public List<DistrictKeyBo> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictKeyBo> districts) {
        this.districts = districts;
    }
}
