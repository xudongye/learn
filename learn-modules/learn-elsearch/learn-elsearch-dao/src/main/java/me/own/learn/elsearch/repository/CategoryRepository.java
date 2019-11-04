package me.own.learn.elsearch.repository;

import me.own.learn.elsearch.po.CategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository  extends ElasticsearchRepository<CategoryDocument, Long> {
}
