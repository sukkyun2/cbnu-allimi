package com.news.noti.notifier;

import com.news.noti.scraper.Article;
import com.news.noti.scraper.ArticleContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@SpringBootTest
@ActiveProfiles("dev")
class FCMPushNotifierTest {

    @Autowired
    private FCMPushNotifier notifier;

    @Test
    void push_test(){
        FCMPushSendRequest fcmPushSendRequest = new FCMPushSendRequest(new FCMPushSendRequest.Notification("테스트", "내용"),
                "fVKnBlYxJiI:APA91bEcHq025bbwX5UwCBPn-U7bVyAlQSykmEpIH5FmgKwNyc29ckpFfNrwW_JVmsuPoizchujLbEeD4KO_tHsgXnMED9NmhzMIy4rZbSO-Ywh831r1yVe2Jow0Zx2KhJmU-jH8ZjxI");

        notifier.sendNotification(fcmPushSendRequest);
    }

    @Test
    void push_test_with_data(){
        List<Article> articles = givenArticles();
        FCMPushSendRequest fcmPushSendRequest = FCMPushSendRequest.from(articles);
        notifier.sendNotification(fcmPushSendRequest);
    }

    List<Article> givenArticles() {
        return List.of(Article.builder()
                .seq(13)
                .categoryName("학부")
                .title("2023학년도 2학기 영어권 SAF 자매대학 파견 교환학생 선발 안내")
                .linkUrl("https://software.cbnu.ac.kr/sub0401/14841")
                .createdAt(LocalDate.now())
                .content(ArticleContent.builder()
                        .imageUrls(Collections.emptyList())
                        .contents("공지내용")
                        .build())
                .build());
    }

}