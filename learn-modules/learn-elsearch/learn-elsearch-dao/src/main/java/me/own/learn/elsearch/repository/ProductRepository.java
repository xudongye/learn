package me.own.learn.elsearch.repository;

import me.own.learn.elsearch.po.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ProductDocument, String> {

    Page<ProductDocument> findAllByProductName(String productName, Pageable pageable);

}
