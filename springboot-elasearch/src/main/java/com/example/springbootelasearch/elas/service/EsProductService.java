package com.example.springbootelasearch.elas.service;

import com.example.springbootelasearch.elas.vo.EsProduct;
import org.springframework.data.domain.Page;

public interface EsProductService {

    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    void delete(Long id);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

    Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);
}
