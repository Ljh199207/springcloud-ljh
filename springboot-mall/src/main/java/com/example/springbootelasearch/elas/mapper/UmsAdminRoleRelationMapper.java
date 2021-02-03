package com.example.springbootelasearch.elas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootelasearch.elas.entity.UmsAdminRoleRelation;
import com.example.springbootelasearch.elas.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

    List<UmsPermission> getPermissionList(Long id);
}
