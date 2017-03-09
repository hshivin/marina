package com.hivin.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/1
 */
@Data
@Component
@ConfigurationProperties(prefix = "jenkins")
public class SecretInfo {
    private String url;
    private String username;
    private String password;
}
