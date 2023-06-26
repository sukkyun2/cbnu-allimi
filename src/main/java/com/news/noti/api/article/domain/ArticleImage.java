package com.news.noti.api.article.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NT_ARTICLE_IMAGE")
@Builder
public class ArticleImage {
    @Id
    @Column(name = "SEQ")
    private Integer articleSeq;
    @Column(name = "IMAGE_URL")
    private String imageUrl;

    public static ArticleImage of(Integer articleSeq, String imageUrl){
        return new ArticleImage(articleSeq, imageUrl);
    }
}
