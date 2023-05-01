package com.news.noti.collector;

import com.news.noti.api.user.query.UserDataDao;
import com.news.noti.notifier.FCMPushNotifier;
import com.news.noti.notifier.FCMPushSendRequest;
import com.news.noti.scraper.CBNUSoftwareArticle;
import com.news.noti.scraper.CBNUSoftwareArticleScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CBNUSoftwareArticleCollector {
    private final CBNUSoftwareArticleScraper scraper;
    private final FCMPushNotifier webPushNotifier;
    private final UserDataDao userDataDao;

    public void collectArticles(CBNUSoftwareArticleCollectRequest req){
        List<CBNUSoftwareArticle> articles = scraper.scrap(req.getTargetDate());

        if(CollectionUtils.isEmpty(articles)) {
            throw new NoArticleException();
        }

        sendNotification(articles);
    }

    private void sendNotification(List<CBNUSoftwareArticle> articles){
        userDataDao.findAll().stream()
                .map(user-> FCMPushSendRequest.of(articles, user.getToken()))
                .forEach(webPushNotifier::sendNotification);
    }
}
