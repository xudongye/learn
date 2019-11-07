package me.own.learn.elsearch.service;

import me.own.learn.elsearch.bo.ProductDocBo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ElsearchService {

    Page<ProductDocBo> getProductByName(String name, int pageNum, int pageSize);

    void saveAll(List<ProductDocBo> productDocBos);

    void save(ProductDocBo productDocBo);

    void delete(String skuNo);
}
