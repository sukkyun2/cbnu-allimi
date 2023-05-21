package com.news.noti.notifier;

import com.news.noti.scraper.Article;
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
    private Notification notification;
    private String to;

    public static FCMPushSendRequest of(List<Article> articles, String token){
        return new FCMPushSendRequest(articles,token);
    }

    public static FCMPushSendRequest from(List<Article> articles){
        return new FCMPushSendRequest(articles, "fVKnBlYxJiI:APA91bEcHq025bbwX5UwCBPn-U7bVyAlQSykmEpIH5FmgKwNyc29ckpFfNrwW_JVmsuPoizchujLbEeD4KO_tHsgXnMED9NmhzMIy4rZbSO-Ywh831r1yVe2Jow0Zx2KhJmU-jH8ZjxI");
    }

    public FCMPushSendRequest(List<Article> articles, String token){
        if(CollectionUtils.isEmpty(articles)){
            throw new IllegalArgumentException("article size should be greater than 0");
        }

        this.notification = Notification.builder()
                .title("새로운 공지사항이 등록 되었습니다.")
                .body(articles.stream().map(Article::getTitle).collect(Collectors.joining("\n")))
                .build();
        this.to = token;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Notification {
        private String title;
        private String body;
    }
}
