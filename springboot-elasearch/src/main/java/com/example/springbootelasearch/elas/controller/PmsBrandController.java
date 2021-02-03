package com.example.springbootelasearch.elas.controller;


import com.example.springbootelasearch.elas.common.CommonResult;
import com.example.springbootelasearch.elas.entity.PmsBrand;
import com.example.springbootelasearch.elas.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author ljh
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/test/pmsBrand")
@Api(tags = "品牌表")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @PostMapping("save")
    @ApiOperation(value = "新增品牌表")
    public CommonResult save(@RequestBody PmsBrand pmsBrand) {
        boolean save = pmsBrandService.save(pmsBrand);
        if (save) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @GetMapping("list")
    @ApiOperation(value = "品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "品牌名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "showStatus", value = "显示状态", paramType = "query", dataType = "int")
    })
    public CommonResult<List<PmsBrand>> list(@ApiIgnore PmsBrand pmsBrand) {
        List<PmsBrand> pmsBrands = pmsBrandService.getList(pmsBrand);
        return CommonResult.success(pmsBrands);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除品牌")
    public CommonResult delete(@PathVariable Long id) {
        boolean b = pmsBrandService.removeById(id);
        if (b) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }


}
