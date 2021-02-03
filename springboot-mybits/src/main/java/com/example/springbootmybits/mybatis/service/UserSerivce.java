package com.example.springbootmybits.mybatis.service;

import java.util.List;
import java.util.Map;

/**
 * @author ljh
 */
public interface UserSerivce {

    List<Map<String, Object>> getAllStrudent();

    List<Map<String, Object>> getAllStrudentByOracle();
}
