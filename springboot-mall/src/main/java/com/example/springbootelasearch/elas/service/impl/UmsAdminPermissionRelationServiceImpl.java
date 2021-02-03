package com.example.springbootelasearch.elas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootelasearch.elas.entity.UmsAdminPermissionRelation;
import com.example.springbootelasearch.elas.mapper.UmsAdminPermissionRelationMapper;
import com.example.springbootelasearch.elas.service.UmsAdminPermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements UmsAdminPermissionRelationService {

}
