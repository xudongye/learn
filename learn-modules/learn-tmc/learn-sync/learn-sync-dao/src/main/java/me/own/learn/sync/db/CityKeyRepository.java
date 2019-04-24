package me.own.learn.sync.db;

import me.own.learn.sync.key.CityKey;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yexudong
 * @date 2019/4/24 12:04
 */
public interface CityKeyRepository extends ElasticsearchRepository<CityKey, String> {
}
