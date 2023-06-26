package com.news.noti.config;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoogleApiAccessTokenProvider {
    private final GoogleCredentials googleCredentials;

    public String getAccessToken(){
        try {
            return googleCredentials.refreshAccessToken().getTokenValue();
        } catch (IOException e) {
            throw new RuntimeException("토큰 발급 실패");
        }
    }
}
