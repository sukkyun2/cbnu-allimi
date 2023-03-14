package com.news.noti.collector;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CBNUSoftwareArticleCollectRequest {
    private LocalDate targetDate;

    public CBNUSoftwareArticleCollectRequest(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
