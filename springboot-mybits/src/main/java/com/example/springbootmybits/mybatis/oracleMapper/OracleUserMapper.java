package com.example.springbootmybits.mybatis.oracleMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author user
 */

@Mapper
public interface OracleUserMapper {

    List<Map<String, Object>> getAllStudents();
}
