package com.example.springbootelasearch.oss.service;

import com.example.springbootelasearch.oss.result.OssCallbackResult;
import com.example.springbootelasearch.oss.result.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

}
