package com.news.noti.notifier;

import com.news.noti.scraper.CBNUSoftwareArticle;
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

    public FCMPushSendRequest(List<CBNUSoftwareArticle> articles) {
        if(CollectionUtils.isEmpty(articles)){
            throw new IllegalArgumentException("article size should be greater than 0");
        }

        this.notification = Notification.builder()
                .title("새로운 공지사항이 등록 되었습니다.")
                .body(articles.stream().map(CBNUSoftwareArticle::getTitle).collect(Collectors.joining("\n")))
                .build();
        this.to = "fVKnBlYxJiI:APA91bEcHq025bbwX5UwCBPn-U7bVyAlQSykmEpIH5FmgKwNyc29ckpFfNrwW_JVmsuPoizchujLbEeD4KO_tHsgXnMED9NmhzMIy4rZbSO-Ywh831r1yVe2Jow0Zx2KhJmU-jH8ZjxI";

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