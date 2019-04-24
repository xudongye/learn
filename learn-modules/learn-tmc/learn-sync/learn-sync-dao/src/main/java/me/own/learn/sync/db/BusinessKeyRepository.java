package me.own.learn.sync.db;

import me.own.learn.sync.key.BusinessKey;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yexudong
 * @date 2019/4/24 15:12
 */
public interface BusinessKeyRepository extends ElasticsearchRepository<BusinessKey, String> {
}
