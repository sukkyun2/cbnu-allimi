package com.news.noti.scraper;

import com.news.noti.api.article.domain.Article;

import java.time.LocalDate;
import java.util.List;

public interface Scraper {
    List<Article> scrap(LocalDate targetDate);
}
