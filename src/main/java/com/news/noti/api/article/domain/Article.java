package com.news.noti.api.article.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NT_ARTICLE")
public class Article {
    @Id
    @Column(name = "SEQ")
    private Integer seq;
    @Column(name = "CATE_NM")
    private String categoryName;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "LINK_URL")
    private String linkUrl;
    @OneToOne(fetch = FetchType.EAGER)
    private ArticleContent content;
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;
}
