package com.news.noti.notifier;

import com.news.noti.api.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FCMPushSendRequest {
    private static final String DEFAULT_IMAGE_URL = "https://www.chungbuk.ac.kr/resource/DATA/editor/202009/1600851013050NKKs4j.png";
    private FCMMessage message;

    public static FCMPushSendRequest of(List<Article> articles, String token) {
        return new FCMPushSendRequest(articles, token, DEFAULT_IMAGE_URL);
    }

    public static FCMPushSendRequest of(List<Article> articles, String token, String imageUrl) {
        return new FCMPushSendRequest(articles, token, imageUrl);
    }

    public FCMPushSendRequest(List<Article> articles, String token, String imageUrl) {
        if (CollectionUtils.isEmpty(articles)) {
            throw new IllegalArgumentException("article size should be greater than 0");
        }

        Notification notification = Notification.builder()
                .title("새로운 공지사항이 등록 되었습니다.")
                .body(articles.stream().map(Article::getTitle).collect(Collectors.joining("\n")))
                .image(imageUrl)
                .build();

        this.message = new FCMMessage(notification, token);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FCMMessage {
        private Notification notification;
        private String token;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
