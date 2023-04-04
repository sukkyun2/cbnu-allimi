package com.news.noti.collector;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CBNUSoftwareArticleCollectorIT {

    @Autowired
    private CBNUSoftwareArticleCollector collector;

    @Test
    void collect_test(){
        collector.collectArticles(new CBNUSoftwareArticleCollectRequest(LocalDate.now()));
    }


}