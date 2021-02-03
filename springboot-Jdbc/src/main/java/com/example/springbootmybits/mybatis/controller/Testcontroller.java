package com.example.springbootmybits.mybatis.controller;

import com.example.springbootmybits.mybatis.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author user
 */
@RestController
public class Testcontroller {

    @Autowired
    private UserSerivce studentService;


    @GetMapping("querystudent/mysql")
    public List<Map<String, Object>> queryStudentsFromMysql(){
        return this.studentService.getAllStrudent();
    }


    @GetMapping("querystudent/oracle")
    public List<Map<String, Object>> queryStudentsFromOracle(){
        return this.studentService.getAllStrudentByOracle();
    }
}
