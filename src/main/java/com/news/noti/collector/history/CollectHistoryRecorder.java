package com.news.noti.collector.history;

import org.springframework.data.repository.Repository;

public interface CollectHistoryRecorder extends Repository<CollectHistory, Integer> {
    void save(CollectHistory history);
}
