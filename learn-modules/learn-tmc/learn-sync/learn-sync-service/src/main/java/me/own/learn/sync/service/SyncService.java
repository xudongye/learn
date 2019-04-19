package me.own.learn.sync.service;

import me.own.learn.sync.bo.CountryBo;

import java.util.List;

/**
 * @author:simonye
 * @date 21:30 2019/4/16
 **/
public interface SyncService {

    /**
     * 同步国家信息
     */
    List<CountryBo> syncCountries();

    /**
     * 同步城市信息
     */
    void syncCities();

    /**
     * 同步酒店信息
     */
    void syncHotels();
}
