package com.news.noti.api.article.domain;

import org.springframework.data.repository.Repository;

public interface ArticleRepository extends Repository<Article, Integer> {
    Article findBySeq(Integer seq);
    Article save(Article article);
}
