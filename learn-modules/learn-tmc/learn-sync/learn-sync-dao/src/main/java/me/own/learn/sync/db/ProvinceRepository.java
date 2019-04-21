package me.own.learn.sync.db;

import me.own.learn.sync.po.Province;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author:simonye
 * @date 22:20 2019/4/20
 **/
public interface ProvinceRepository extends ElasticsearchRepository<Province, String> {
}
