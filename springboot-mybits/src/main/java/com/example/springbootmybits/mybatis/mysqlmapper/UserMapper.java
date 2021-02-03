package com.example.springbootmybits.mybatis.mysqlmapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author user
 */

@Mapper
public interface UserMapper {

    List<Map<String, Object>> getAllStudents();
}
