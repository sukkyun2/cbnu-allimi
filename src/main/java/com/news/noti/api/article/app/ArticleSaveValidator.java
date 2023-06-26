package com.news.noti.api.article.app;

import com.news.noti.api.article.domain.Article;
import com.news.noti.api.article.domain.ArticleContent;
import com.news.noti.api.common.app.ValidateException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


import static com.news.noti.api.article.app.ArticleSaveValidateMessage.*;

public class ArticleSaveValidator {

    public void validate(List<Article> articles) {
        List<String> errors = new ArrayList<>();

        if (articles.stream().map(Article::getTitle).anyMatch(StringUtils::isEmpty)) {
            errors.add(ERROR_MSG_TITLE_IS_NOT_EMPTY);
        }

        if (articles.stream().map(Article::getCategoryName).anyMatch(StringUtils::isEmpty)) {
            errors.add(ERROR_MSG_CATEGORY_NAME_IS_NOT_EMPTY);
        }

        if (articles.stream().map(Article::getLinkUrl).anyMatch(StringUtils::isEmpty)) {
            errors.add(ERROR_MSG_LINK_URL_IS_NOT_EMPTY);
        }

        List<ArticleContent> articleContents = articles.stream().map(Article::getContent).toList();

        if (articleContents.stream().map(ArticleContent::getContents).anyMatch(StringUtils::isEmpty)) {
            errors.add(ERROR_MSG_ARTICLE_CONTENT_IS_NOT_EMPTY);
        }

        if (!errors.isEmpty()) {
            throw new ValidateException(errors.get(0));
        }
    }
}
