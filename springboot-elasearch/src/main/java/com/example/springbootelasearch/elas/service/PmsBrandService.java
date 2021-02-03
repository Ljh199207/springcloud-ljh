package com.example.springbootelasearch.elas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootelasearch.elas.entity.PmsBrand;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
public interface PmsBrandService extends IService<PmsBrand> {

    List<PmsBrand> getList(PmsBrand pmsBrand);
}
