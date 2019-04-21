package me.own.learn.sync.db;

import me.own.learn.sync.po.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author:simonye
 * @date 22:19 2019/4/20
 **/
public interface CityRepository extends ElasticsearchRepository<City, String> {
}
