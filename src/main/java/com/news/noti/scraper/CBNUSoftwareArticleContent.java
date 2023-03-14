package com.news.noti.scraper;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CBNUSoftwareArticleContent {
    private String contents;
    private List<String> imageUrls;
}
