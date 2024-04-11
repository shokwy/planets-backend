package com.shok.planets.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wy
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "planets.oss")
@Data
public class TencentOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
