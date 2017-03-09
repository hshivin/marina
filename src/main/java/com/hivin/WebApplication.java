package com.hivin;


import com.hivin.vo.SecretInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hivin
 */
@EnableAutoConfiguration
@EnableConfigurationProperties({SecretInfo.class})
@ComponentScan("com.hivin.*")
@SpringBootApplication
@EnableTransactionManagement
public class WebApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
}