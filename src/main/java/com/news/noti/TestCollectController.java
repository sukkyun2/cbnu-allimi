package com.news.noti;

import com.news.noti.api.common.api.ApiResponse;
import com.news.noti.collector.NoArticleException;
import com.news.noti.collector.CBNUSoftwareArticleCollectRequest;
import com.news.noti.collector.CBNUSoftwareArticleCollector;
import com.news.noti.notifier.NotifyException;
import com.news.noti.scraper.ScrapException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class TestCollectController {
    private final CBNUSoftwareArticleCollector collector;

    @GetMapping("/articles")
    public ApiResponse<Void> collectArticles(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate targetDate){
        try {
            collector.collectArticles(new CBNUSoftwareArticleCollectRequest(targetDate));

            return ApiResponse.ok();
        } catch (NoArticleException e) {
            return ApiResponse.ok(e.getMessage());
        } catch (ScrapException | NotifyException e){
            return ApiResponse.failWithMessage("게시물 수집 실패");
        }
    }
}
