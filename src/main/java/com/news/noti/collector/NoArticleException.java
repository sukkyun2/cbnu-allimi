package com.news.noti.collector;

public class NoArticleException extends RuntimeException {
    private static String DEFAULT_ERROR_MSG = "수집된 게시물이 없습니다";

    public NoArticleException() {
        super(DEFAULT_ERROR_MSG);
    }
}
