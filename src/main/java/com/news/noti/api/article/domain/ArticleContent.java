package com.news.noti.api.article.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "NT_ARTICLE_CONTENT")
public class ArticleContent {

    @Id
    @Column(name = "SEQ")
    private Integer articleSeq;

    @Column(name = "CONTENTS")
    @Lob
    private String contents;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ArticleImage> imageUrls;

    @Builder
    public ArticleContent(Integer articleSeq, String contents, List<String> imageUrls) {
        this.articleSeq = articleSeq;
        this.contents = contents;
        this.imageUrls = imageUrls.stream()
                .map(imageUrl -> ArticleImage.of(articleSeq, imageUrl))
                .toList();
    }

    public List<String> getImageUrls() {
        return imageUrls.stream().map(ArticleImage::getImageUrl).toList();
    }
}
