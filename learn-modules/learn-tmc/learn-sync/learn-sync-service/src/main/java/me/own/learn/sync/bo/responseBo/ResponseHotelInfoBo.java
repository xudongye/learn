package me.own.learn.sync.bo.responseBo;

import me.own.learn.sync.bo.HotelInfoBo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/25 11:31
 */
public class ResponseHotelInfoBo {
    private List<HotelInfoBo> hotelInfos;

    public List<HotelInfoBo> getHotelInfos() {
        return hotelInfos;
    }

    public void setHotelInfos(List<HotelInfoBo> hotelInfos) {
        this.hotelInfos = hotelInfos;
    }
}
