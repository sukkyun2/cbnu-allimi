package com.news.noti.scraper;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CBNUSoftwareArticle {
    private Integer seq;
    private String categoryName;
    private String title;
    private String linkUrl;
    private CBNUSoftwareArticleContent content;
    private LocalDate createdAt;
}
