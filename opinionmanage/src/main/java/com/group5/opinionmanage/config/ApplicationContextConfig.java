package com.group5.opinionmanage.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 10569
 * @version 1.0
 * @description
 * @Date 2022/8/24 14:13
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRetTemplate(){
        return new RestTemplate();
    }
}


