package com.example.springbootelasearch.elas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootelasearch.elas.entity.UmsAdmin;
import com.example.springbootelasearch.elas.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    UmsAdmin register(UmsAdmin umsAdminParam);

    String login(String username, String password);
}