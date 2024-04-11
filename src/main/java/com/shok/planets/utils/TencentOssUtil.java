package com.shok.planets.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Data
@AllArgsConstructor
@Slf4j
public class TencentOssUtil {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;


    public String upload(MultipartFile file) {
// 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID 和 SECRETKEY 请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理

        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        Region region = new Region(endpoint);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        String key = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();
        String datetime = new DateTime().toString("yyyy.MM/dd");
        key = datetime + "/" + key;
        try {
            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);

            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return "https://"+bucketName+".cos."+endpoint+".myqcloud.com"+"/"+key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
