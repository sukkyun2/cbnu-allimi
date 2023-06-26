package com.news.noti.collector.history;

import com.news.noti.api.article.app.ArticleSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectEventHandler {
    private final CollectHistoryRecorder historyRecorder;
    private final ArticleSaveService articleSaveService;

    @EventListener
    public void recordStatus(CollectStatus status) {
        historyRecorder.save(CollectHistory.fromStatus(status));
    }

    @EventListener
    public void processPostCollect(CollectEvent event) {
        recordStatus(event.getCollectStatus());

        articleSaveService.saveArticles(event.getArticles());
    }
}

