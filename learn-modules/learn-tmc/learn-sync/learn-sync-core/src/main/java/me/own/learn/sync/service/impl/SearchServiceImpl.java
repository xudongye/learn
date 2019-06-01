package me.own.learn.sync.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.bo.BusinessKeyBo;
import me.own.learn.sync.bo.CityKeyBo;
import me.own.learn.sync.bo.DistrictKeyBo;
import me.own.learn.sync.db.BusinessKeyRepository;
import me.own.learn.sync.db.CityKeyRepository;
import me.own.learn.sync.db.DistrictKeyRepository;
import me.own.learn.sync.key.BusinessKey;
import me.own.learn.sync.key.CityKey;
import me.own.learn.sync.key.DistrictKey;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.vo.BusinessKeyVo;
import me.own.learn.sync.vo.CityKeyVo;
import me.own.learn.sync.vo.DistrictKeyVo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * @author yexudong
 * @date 2019/4/19 16:06
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private CityKeyRepository cityKeyRepository;
    @Autowired
    private DistrictKeyRepository districtKeyRepository;
    @Autowired
    private BusinessKeyRepository businessKeyRepository;


    @Override
    public Page<CityKeyVo> findByCityKey(String keyword, int pageNum, int pageSize) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (keyword != null) {
            queryBuilder.should(QueryBuilders.matchQuery("cityName", keyword).boost(4));
            queryBuilder.should(QueryBuilders.matchQuery("provinceName", keyword).boost(3));
            queryBuilder.should(QueryBuilders.matchQuery("countryName", keyword).boost(2));
            queryBuilder.minimumNumberShouldMatch(1);
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        Page<CityKey> areaKeys = cityKeyRepository.search(query);
        return areaKeys.map(new Converter<CityKey, CityKeyVo>() {
            @Override
            public CityKeyVo convert(CityKey cityKey) {
                return Mapper.Default().map(cityKey, CityKeyVo.class);
            }
        });
    }

    @Override
    public Page<DistrictKeyVo> findByDistrictKey(String keyword, int pageNum, int pageSize) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (keyword != null) {
            queryBuilder.should(QueryBuilders.matchQuery("districtName", keyword).boost(4));
            queryBuilder.minimumNumberShouldMatch(1);
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        Page<DistrictKey> districtKeys = districtKeyRepository.search(query);
        return districtKeys.map(new Converter<DistrictKey, DistrictKeyVo>() {
            @Override
            public DistrictKeyVo convert(DistrictKey districtKey) {
                return Mapper.Default().map(districtKey, DistrictKeyVo.class);
            }
        });
    }

    @Override
    public Page<BusinessKeyVo> findByBusinessKey(String keyword, int pageNum, int pageSize) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (keyword != null) {
            queryBuilder.should(QueryBuilders.matchQuery("businessName", keyword).boost(4));
            queryBuilder.minimumNumberShouldMatch(1);
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        Page<BusinessKey> businessKeys = businessKeyRepository.search(query);
        return businessKeys.map(new Converter<BusinessKey, BusinessKeyVo>() {
            @Override
            public BusinessKeyVo convert(BusinessKey businessKey) {
                return Mapper.Default().map(businessKey, BusinessKeyVo.class);
            }
        });
    }

    @Override
    public CityKeyVo insertCityKey(CityKeyBo cityKeyBo) {
        CityKey cityKey = Mapper.Default().map(cityKeyBo, CityKey.class);
        cityKeyRepository.index(cityKey);
        LOGGER.info("insert new key for city {}", cityKeyBo.getCityName());
        return Mapper.Default().map(cityKey, CityKeyVo.class);
    }

    @Override
    public DistrictKeyVo insertDistrictKey(DistrictKeyBo districtKeyBo) {
        DistrictKey districtKey = Mapper.Default().map(districtKeyBo, DistrictKey.class);
        districtKeyRepository.index(districtKey);
        LOGGER.info("insert new key for district {}", districtKey.getDistrictName());
        return Mapper.Default().map(districtKey, DistrictKeyVo.class);
    }

    @Override
    public BusinessKeyVo insertBusinessKey(BusinessKeyBo businessKeyBo) {
        BusinessKey businessKey = Mapper.Default().map(businessKeyBo, BusinessKey.class);
        businessKeyRepository.index(businessKey);
        LOGGER.info("insert new key for business {}", businessKey.getBusinessName());
        return Mapper.Default().map(businessKey, BusinessKeyVo.class);
    }
}
