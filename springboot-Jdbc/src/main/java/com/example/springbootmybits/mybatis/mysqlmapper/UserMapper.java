package com.example.springbootmybits.mybatis.mysqlmapper;

import java.util.List;
import java.util.Map;

/**
 * @author user
 */

public interface UserMapper {

    List<Map<String, Object>> getAllStudents();
}
