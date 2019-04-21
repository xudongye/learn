package me.own.learn.sync.service.impl;

import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.bo.CountryBo;
import me.own.learn.sync.db.CityRepository;
import me.own.learn.sync.db.CountryRepository;
import me.own.learn.sync.db.ProvinceRepository;
import me.own.learn.sync.exception.CountryExistException;
import me.own.learn.sync.exception.CountryNotFoundException;
import me.own.learn.sync.po.Country;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.vo.CountryVo;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 16:06
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    /* 分页参数 */
    private int PAGE_SIZE = 12;          // 每页数量
    private int DEFAULT_PAGE_NUMBER = 0; // 默认当前页码
    /* 搜索模式 */
    private String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    private Float MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CountryVo> findByCountryName(String countryName) {
        List<Country> countries = countryRepository.findCountriesByCountryName(countryName);
        return Mapper.Default().mapArray(countries, CountryVo.class);
    }

    @Override
    public CountryVo findByCountryCode(String countryCode) {
        Country country = countryRepository.findByCountryCode(countryCode);
        if (country == null) {
            throw new CountryNotFoundException();
        }
        return Mapper.Default().map(country, CountryVo.class);
    }

    @Override
    public CountryVo save(CountryBo countryBo) {
        Country country = countryRepository.findByCountryCode(countryBo.getCountryCode());
        if (country != null) {
            throw new CountryExistException();
        }
        country = Mapper.Default().map(countryBo, Country.class);
        countryRepository.index(country);
        LOGGER.info("sync country {}", country.getCountryName());
        return Mapper.Default().map(country, CountryVo.class);
    }

    @Override
    public CountryVo refresh(CountryBo countryBo) {
        Country country = countryRepository.findByCountryCode(countryBo.getCountryCode());
        if (country == null) {
            throw new CountryNotFoundException();
        }
        countryRepository.delete(country);
        Country newCountry = Mapper.Default().map(countryBo, Country.class);
        countryRepository.index(newCountry);
        return Mapper.Default().map(newCountry, CountryVo.class);
    }

    /**
     * 根据搜索词构造搜索查询语句
     * <p>
     * 代码流程：
     * - 权重分查询
     * - 短语匹配
     * - 设置权重分最小值
     * - 设置分页参数
     *
     * @param pageNumber    当前页码
     * @param pageSize      每页大小
     * @param searchContent 搜索内容
     * @return
     */
    private SearchQuery searchQuery(int pageNumber, int pageSize, String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/c ... .html
        //   - 短语匹配 https://www.elastic.co/guide/c ... .html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);
        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

    }
}
