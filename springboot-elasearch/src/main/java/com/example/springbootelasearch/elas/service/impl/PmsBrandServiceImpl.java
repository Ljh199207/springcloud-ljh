package com.example.springbootelasearch.elas.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootelasearch.elas.entity.PmsBrand;
import com.example.springbootelasearch.elas.mapper.PmsBrandMapper;
import com.example.springbootelasearch.elas.service.PmsBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public List<PmsBrand> getList(PmsBrand pmsBrand) {
        Wrapper wrapper = getWapper(pmsBrand);
        List<PmsBrand> pmsBrands =  baseMapper.selectList(wrapper);
        return pmsBrands;
    }

    public Wrapper getWapper(PmsBrand pmsBrand) {
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(pmsBrand.getName())) {
            wrapper.eq("name", pmsBrand.getName());
        }
        if (pmsBrand.getShowStatus() != null) {
            wrapper.eq("show_status", pmsBrand.getShowStatus());
        }
        return wrapper;
    }
}
