package com.example.springbootmybits.mybatis.service.impl;

import com.example.springbootmybits.mybatis.mysqlmapper.UserMapper;
import com.example.springbootmybits.mybatis.oracleMapper.OracleUserMapper;
import com.example.springbootmybits.mybatis.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author user
 */
@Service("studentService")
public class UserServiceImpl implements UserSerivce {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OracleUserMapper oracleUserMapper;

    @Override
    public List<Map<String, Object>> getAllStrudent() {
        return this.userMapper.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStrudentByOracle() {
        return this.oracleUserMapper.getAllStudents();
    }


}
