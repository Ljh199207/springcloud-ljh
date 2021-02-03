package com.example.springbootelasearch.oss.controller;

import com.example.springbootelasearch.elas.common.CommonResult;
import com.example.springbootelasearch.oss.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "OssController")
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssUtil ossUtil;


    @GetMapping("upload")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam()
    public CommonResult upload(MultipartFile multipartFile) {
        return CommonResult.failed();
    }

    @GetMapping("uploadMessage")
    @ApiOperation(value = "字符串上传")
    public CommonResult uploadMessage(@RequestParam String message, @RequestParam String key) {
        Object o = ossUtil.upMessage(message, key);
        return CommonResult.success(o);
    }

}
