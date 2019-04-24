package me.own.learn.sync.service;

import me.own.learn.sync.vo.BusinessKeyVo;
import me.own.learn.sync.vo.CountryVo;
import me.own.learn.sync.vo.DistrictKeyVo;
import me.own.learn.sync.vo.ProvinceVo;

import java.util.List;

/**
 * @author:simonye
 * @date 21:30 2019/4/16
 **/
public interface SyncService {

    /**
     * 匹配国家信息
     *
     * @param countryNames
     * @return
     */
    List<CountryVo> matchCountries(String[] countryNames);

    /**
     * 同步城市信息
     *
     * @param countryCode
     * @param countryName
     * @return
     */
    List<ProvinceVo> syncCities(String countryCode, String countryName);


    List<DistrictKeyVo> syncDistricts(String cityCode);

    List<BusinessKeyVo> syncBusiness(String cityCode);

    /**
     * 同步酒店信息
     *
     * @param cityCode
     * @param hotelName
     */
    void syncHotels(String cityCode, String hotelName);
}
