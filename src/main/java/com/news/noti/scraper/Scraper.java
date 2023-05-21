package com.news.noti.scraper;

import java.time.LocalDate;
import java.util.List;

public interface Scraper {
    List<Article> scrap(LocalDate targetDate);
}
