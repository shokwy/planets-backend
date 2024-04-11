package com.shok.planets.config;


import com.shok.planets.properties.TencentOssProperties;
import com.shok.planets.utils.TencentOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wy
 * @version 1.0
 */

@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TencentOssUtil constantPropertiesUtil(TencentOssProperties tencentOssProperties){
        log.info("开始创建腾讯云文件上传工具类对象：{}",tencentOssProperties);
        return new TencentOssUtil(tencentOssProperties.getEndpoint(),
                tencentOssProperties.getAccessKeyId(),
                tencentOssProperties.getAccessKeySecret(),
                tencentOssProperties.getBucketName());
    }
}
