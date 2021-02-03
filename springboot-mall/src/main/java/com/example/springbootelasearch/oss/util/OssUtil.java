package com.example.springbootelasearch.oss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
@Slf4j
public class OssUtil {

    private static String bucketName = "esljh-oss";

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
    private static String firstKey = "my-first-key";
    @Autowired
    private OSS ossClient;


    public void getBuckInfo(String bucketName) {
        ossClient.getBucketInfo(bucketName);
        BucketInfo bucketInfo = ossClient.getBucketInfo(bucketName);
        System.out.println("Bucket " + bucketName + "的信息如下：");
        System.out.println("\t数据中心：" + bucketInfo.getBucket().getLocation());
        System.out.println("\t创建时间：" + bucketInfo.getBucket().getCreationDate());
        System.out.println("\t用户标志：" + bucketInfo.getBucket().getOwner());
    }


    public Object upMessage(String message, String key) {
        InputStream inputStream = new ByteArrayInputStream(message.getBytes());
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, key, inputStream);

        log.info("Object：" + key + "存入OSS成功。", putObjectResult);
        return putObjectResult;
    }

}
