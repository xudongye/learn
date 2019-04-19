package me.own.learn.sync.service.impl;

import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.bo.CountryBo;
import me.own.learn.sync.db.CountryRepository;
import me.own.learn.sync.po.Country;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.vo.CountryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 16:06
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryVo> findByCountryName(String countryName) {
        List<Country> countries = countryRepository.findCountriesByCountryName(countryName);
        return Mapper.Default().mapArray(countries, CountryVo.class);
    }

    @Override
    public CountryVo save(CountryBo countryBo) {
        Country country = Mapper.Default().map(countryBo, Country.class);
        countryRepository.index(country);
        LOGGER.info("sync country {}", country.getCountryName());
        return Mapper.Default().map(country, CountryVo.class);
    }
}
