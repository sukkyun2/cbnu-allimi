package com.news.noti.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FirebaseInit {
    private final GoogleFcmConfig config;

    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        return GoogleCredentials
                .fromStream(new ClassPathResource(config.getServiceAccountPath()).getInputStream())
                .createScoped(config.getCredentialsScopes());
    }

    @ConfigurationProperties(prefix = "fcm")
    @Component
    @Data
    public static class GoogleFcmConfig {
        private String serviceAccountPath;
        private List<String> credentialsScopes;
    }
}
