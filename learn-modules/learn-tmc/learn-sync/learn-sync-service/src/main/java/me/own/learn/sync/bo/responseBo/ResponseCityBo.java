package me.own.learn.sync.bo.responseBo;

import me.own.learn.sync.bo.ProvinceBo;

import java.util.List;

/**
 * @author:simonye
 * @date 16:30 2019/4/21
 **/
public class ResponseCityBo {
    private List<ProvinceBo> provinces;

    public List<ProvinceBo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceBo> provinces) {
        this.provinces = provinces;
    }
}
