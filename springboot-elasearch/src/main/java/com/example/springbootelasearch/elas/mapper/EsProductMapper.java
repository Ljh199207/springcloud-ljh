package com.example.springbootelasearch.elas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootelasearch.elas.vo.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EsProductMapper extends BaseMapper<EsProduct> {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);

}
