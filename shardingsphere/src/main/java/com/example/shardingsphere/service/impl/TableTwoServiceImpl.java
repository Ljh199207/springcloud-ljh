package com.example.shardingsphere.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingsphere.entity.TableTwo;
import com.example.shardingsphere.mapper.TableTwoMapper;
import com.example.shardingsphere.service.TableTwoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 表2 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2020-10-09
 */
@Service
public class TableTwoServiceImpl extends ServiceImpl<TableTwoMapper, TableTwo> implements TableTwoService {

}
