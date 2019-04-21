package me.own.learn.sync.db;

import me.own.learn.sync.po.Country;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 13:04
 */
public interface CountryRepository extends ElasticsearchRepository<Country, String> {

    List<Country> findCountriesByCountryName(String countryName);

    Country findByCountryCode(String countryCode);

}
