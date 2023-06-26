package com.news.noti.api.article.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Transactional
    void save() {
        Article article = given_article();

//        Article savedArticle = articleRepository.save(article);

//        articleRepository.save(article);
        Article savedArticle = articleRepository.findBySeq(13);

        ArticleContent content = savedArticle.getContent();
        List<String> imageUrls = content.getImageUrls();
        System.out.println(savedArticle);
    }

    private Article given_article() {
        return Article.builder()
                .seq(13)
                .categoryName("학부")
                .title("2023학년도 2학기 영어권 SAF 자매대학 파견 교환학생 선발 안내")
                .linkUrl("https://software.cbnu.ac.kr/sub0401/14841")
                .createdAt(LocalDate.now())
                .content(ArticleContent.builder()
                        .articleSeq(13)
                        .imageUrls(Collections.singletonList("https://software.cbnu.ac.kr/files/attach/images/197/336/015/d2750efadb08c4822946c4d446869ca9.jpg"))
                        .contents("공지내용")
                        .build())
                .build();
    }

}