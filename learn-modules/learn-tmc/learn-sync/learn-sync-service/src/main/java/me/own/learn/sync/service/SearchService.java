package me.own.learn.sync.service;

import me.own.learn.sync.bo.CountryBo;
import me.own.learn.sync.vo.CountryVo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 16:03
 */
public interface SearchService {

    List<CountryVo> findByCountryName(String countryName);

    CountryVo findByCountryCode(String countryCode);

    CountryVo save(CountryBo countryBo);

    CountryVo refresh(CountryBo countryBo);
}
