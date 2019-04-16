package me.own.learn.sync.service;

/**
 * @author:simonye
 * @date 21:30 2019/4/16
 **/
public interface SyncService {

    /**
     * 同步国家信息
     */
    void syncCountries();

    /**
     * 同步城市信息
     */
    void syncCities();

    /**
     * 同步酒店信息
     */
    void syncHotels();
}
