package com.example.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class OrderServiceConfig {

    @Bean
    Retryer retryer(){
        return new Retryer.Default(100, 1000, 3);
    }

    @Bean
    Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
