package com.news.noti.collector;

import com.news.noti.api.user.query.UserDataDao;
import com.news.noti.notifier.FCMPushNotifier;
import com.news.noti.notifier.FCMPushSendRequest;
import com.news.noti.api.article.domain.Article;
import com.news.noti.scraper.CBNUSoftwareArticleScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CBNUSoftwareArticleCollector {
    private final CBNUSoftwareArticleScraper scraper;
    private final FCMPushNotifier webPushNotifier;
    private final UserDataDao userDataDao;

    public List<Article> collectArticles(CBNUSoftwareArticleCollectRequest req) {
        List<Article> articles = scraper.scrap(req.getTargetDate());

        if (CollectionUtils.isEmpty(articles)) {
            return Collections.emptyList();
        }

        sendNotification(articles);

        return articles;
    }

    private void sendNotification(List<Article> articles) {
        userDataDao.findAll().stream()
                .map(user -> FCMPushSendRequest.of(articles, user.getToken()))
                .forEach(webPushNotifier::sendNotification);
    }
}
