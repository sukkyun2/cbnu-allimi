package com.news.noti.scraper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class CBNUSoftwareScraperIT {
    private CBNUSoftwareArticleScraper scraper;

    @BeforeEach
    void setUp() {
        scraper = new CBNUSoftwareArticleScraper("https://software.cbnu.ac.kr/sub0401");
    }

    @Test
    void scrap_test() {
        List<Article> scrap = scraper.scrap(LocalDate.now());
    }

}