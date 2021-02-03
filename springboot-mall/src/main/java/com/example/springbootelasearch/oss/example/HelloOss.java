package com.example.springbootelasearch.oss.example;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.GetObjectRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloOss {

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI4GHgYFrU1NEMEyViWe7K";
    private static String accessKeySecret = "dk8R0KzevJBAnDBVMFkZRDwsZv0rkx";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName = "esljh-oss";

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
    private static String firstKey = "my-first-key";

    public static void main(String[] args) {
        log.info("Started");
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
        try {
            if (ossClient.doesBucketExist(bucketName)) {
                System.out.println("您已经创建Bucket：" + bucketName + "。");
            } else {
                ossClient.createBucket(bucketName);
            }
            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            BucketInfo bucketInfo = ossClient.getBucketInfo(bucketName);
            System.out.println("Bucket " + bucketName + "的信息如下：");
            System.out.println("\t数据中心：" + bucketInfo.getBucket().getLocation());
            System.out.println("\t创建时间：" + bucketInfo.getBucket().getCreationDate());
            System.out.println("\t用户标志：" + bucketInfo.getBucket().getOwner());

            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
           /* InputStream inputStream = new ByteArrayInputStream("Hello OSS".getBytes());
            ossClient.putObject(bucketName, firstKey, inputStream);
            System.out.println("Object：" + firstKey + "存入OSS成功。");

            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            OSSObject ossObject = ossClient.getObject(bucketName, firstKey);
            InputStream in = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                objectContent.append(line);
            }

            in.close();
            reader.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent.toString());
*/
            //本地下载
            /*ossClient.getObject(new GetObjectRequest(bucketName, firstKey), new File("D:\\home\\excel\\helloOss.txt"));
            ossClient.shutdown();*/
            //范围下载
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, firstKey);
          /*  getObjectRequest.setRange(100, 200);
            getObjectRequest.addHeader("x-oss-range-behavior", "standard");
            //本地下载
            //ossClient.getObject(getObjectRequest,new File("D:\\home\\excel\\helloOss.txt"));
           OSSObject ossObject = ossClient.getObject(getObjectRequest);*/

            try {
                // 范围首端取值不在有效区间，以下代码会抛出异常。返回HTTP Code为416，错误码为InvalidRange。
                getObjectRequest = new GetObjectRequest(bucketName, firstKey);
                getObjectRequest.setRange(1000, 2000);
                getObjectRequest.addHeader("x-oss-range-behavior", "standard");
                ossClient.getObject(getObjectRequest);
            } catch (OSSException e) {
                System.out.println("standard get " + "1000~2000:" + "error code:" + e.getErrorCode());
            }


       /*     byte[] buf = new byte[2];
            InputStream in = ossObject.getObjectContent();
            for (int n = 0; n != -1; ) {
                n = in.read(buf, 0, buf.length);
                if(n!=-1){
                    System.out.println(new String(buf));
                }
            }
            in.close();*/
            ossClient.shutdown();
        } catch (Exception e) {
        }
    }
}
