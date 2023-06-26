package com.news.noti.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
@RequiredArgsConstructor
public class FCMFeignConfiguration {
    private final GoogleApiAccessTokenProvider tokenProvider;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate
                        .header("Authorization", "Bearer " + tokenProvider.getAccessToken())
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }
}
