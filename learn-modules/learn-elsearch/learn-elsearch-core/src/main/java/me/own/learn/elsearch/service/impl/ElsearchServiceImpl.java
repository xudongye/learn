package me.own.learn.elsearch.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.elsearch.bo.ProductDocBo;
import me.own.learn.elsearch.po.ProductDocument;
import me.own.learn.elsearch.repository.ProductRepository;
import me.own.learn.elsearch.service.ElsearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElsearchServiceImpl implements ElsearchService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDocBo> getProductByName(String name, int pageNum, int pageSize) {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.withQuery(QueryBuilders.matchQuery("productName", name));
        //分页参数
        Pageable pageable = new PageRequest(pageNum, pageSize);

        queryBuilder.withPageable(pageable);

        Page<ProductDocument> productDocuments = productRepository.search(queryBuilder.build());

        return productDocuments.map(new Converter<ProductDocument, ProductDocBo>() {
            @Override
            public ProductDocBo convert(ProductDocument productDocument) {
                return Mapper.Default().map(productDocument, ProductDocBo.class);
            }
        });
    }

    @Override
    public void saveAll(List<ProductDocBo> productDocBos) {
        List<ProductDocument> productDocuments = Mapper.Default().mapArray(productDocBos, ProductDocument.class);
        productRepository.save(productDocuments);
    }

    @Override
    public void save(ProductDocBo productDocBo) {
        productRepository.save(Mapper.Default().map(productDocBo, ProductDocument.class));
    }

    @Override
    public void delete(String skuNo) {
        productRepository.delete(skuNo);
    }
}
