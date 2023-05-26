package com.news.noti.schedule;

import com.news.noti.collector.CBNUSoftwareArticleCollectRequest;
import com.news.noti.collector.CBNUSoftwareArticleCollector;
import com.news.noti.collector.NoArticleException;
import com.news.noti.collector.history.CollectEvent;
import com.news.noti.collector.history.CollectStatus;
import com.news.noti.scraper.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleRunner {
    private final CBNUSoftwareArticleCollector collector;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 3 12-18 * * 1-5")
    public void run(){
        try {
            List<Article> articles = collector.collectArticles(new CBNUSoftwareArticleCollectRequest(LocalDate.now()));

            eventPublisher.publishEvent(new CollectEvent(articles));
        } catch (Exception e){
            log.error(e.getMessage());
            eventPublisher.publishEvent(CollectStatus.ERROR);
        }
    }
}
