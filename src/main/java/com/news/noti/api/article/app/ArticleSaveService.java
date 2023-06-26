package com.news.noti.api.article.app;

import com.news.noti.api.article.domain.Article;
import com.news.noti.api.article.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleSaveService {
    private final ArticleRepository articleRepository;
    private final ArticleSaveValidator validator = new ArticleSaveValidator();

    @Transactional
    public void saveArticles(List<Article> articles){
        validator.validate(articles);

        articles.forEach(articleRepository::save);
    }
}
