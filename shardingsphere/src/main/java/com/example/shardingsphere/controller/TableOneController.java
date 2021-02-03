package com.example.shardingsphere.controller;


import com.example.shardingsphere.common.CommonResult;
import com.example.shardingsphere.entity.TableOne;
import com.example.shardingsphere.service.TableOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 表1 前端控制器
 * </p>
 *
 * @author ljh
 * @since 2020-10-09
 */
@Controller
@RequestMapping("/test/tableOne")
public class TableOneController {

    @Autowired
    private TableOneService tableOneService;

    public CommonResult getList() {
        List<TableOne> list = tableOneService.list();
        return CommonResult.success(list);
    }
}
