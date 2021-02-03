package com.example.shardingsphere.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingsphere.entity.TableOne;
import com.example.shardingsphere.mapper.TableOneMapper;
import com.example.shardingsphere.service.TableOneService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 表1 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2020-10-09
 */
@Service
public class TableOneServiceImpl extends ServiceImpl<TableOneMapper, TableOne> implements TableOneService {

}
