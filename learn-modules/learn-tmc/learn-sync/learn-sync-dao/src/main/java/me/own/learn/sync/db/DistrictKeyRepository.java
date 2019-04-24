package me.own.learn.sync.db;

import me.own.learn.sync.key.DistrictKey;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yexudong
 * @date 2019/4/24 15:07
 */
public interface DistrictKeyRepository extends ElasticsearchRepository<DistrictKey, String> {
}
