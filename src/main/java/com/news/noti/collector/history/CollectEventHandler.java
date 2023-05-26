package com.news.noti.collector.history;

import com.news.noti.scraper.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.news.noti.collector.history.CollectStatus.SUCCESS;

@Component
@RequiredArgsConstructor
public class CollectEventHandler {
    private final CollectHistoryRecorder historyRecorder;

    @EventListener
    public void recordStatus(CollectStatus status) {
        historyRecorder.save(CollectHistory.fromStatus(status));
    }

    @EventListener
    public void processPostCollect(CollectEvent event) {
        recordStatus(event.getCollectStatus());

        //TODO article 저장로직 추가
    }
}

