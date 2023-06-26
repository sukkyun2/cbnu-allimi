package com.news.noti.notifier;

import com.news.noti.api.article.domain.Article;
import com.news.noti.api.article.domain.ArticleContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

class SlackNotifierIT {
    private SlackNotifier slackNotifier;

    @BeforeEach
    void setUp() {
        slackNotifier = new SlackNotifier("https://hooks.slack.com/services/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");
    }

    @Test
    void slack_new_message_test() {
        List<Article> articles = givenArticles();

        slackNotifier.sendNotification(new SlackNotifyRequest(articles));
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