package com.news.noti.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class FCMFeignConfiguration {
    private final String privateKey;

    public FCMFeignConfiguration(@Value("${fcm.privateKey}") String privateKey) {
        this.privateKey = privateKey;
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate
                        .header("Authorization", "key=" + privateKey)
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }
}
