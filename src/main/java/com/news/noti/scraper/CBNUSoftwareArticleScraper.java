package com.news.noti.scraper;

import com.news.noti.api.article.domain.Article;
import com.news.noti.api.article.domain.ArticleContent;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CBNUSoftwareArticleScraper implements Scraper {
    private final String cbnuSoftwareUrl;

    public CBNUSoftwareArticleScraper(@Value("${cbnusoftware.url}") String cbnuSoftwareUrl) {
        this.cbnuSoftwareUrl = cbnuSoftwareUrl;
    }

    @Override
    public List<Article> scrap(LocalDate targetDate) {
        Connection connect = Jsoup.connect(cbnuSoftwareUrl);

        try {
            return Optional.of(connect.get())
                    .map(document -> document.select("tbody tr:not(.notice)"))
                    .map(elements -> elements.stream()
                            .map(this::convert)
                            .filter(filterNewArticle(targetDate))
                            .toList())
                    .orElseThrow();

        } catch (IOException e) {
            e.printStackTrace();
            throw new ScrapException();
        }
    }

    private Article convert(Element element) {
        Integer seq = Integer.valueOf(element.getElementsByClass("m_no").first().text());
        String href = element.selectFirst(".title > a").attr("href");

        return Article.builder()
                .seq(seq)
                .categoryName(element.getElementsByClass("cate").first().text())
                .title(element.select(".title > a").first().text())
                .linkUrl(href)
                .content(getContent(seq, href))
                .createdAt(LocalDate.parse(element.getElementsByClass("time").first().text(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }

    @SneakyThrows
    private ArticleContent getContent(Integer seq, String linkUrl) {
        Connection connect = Jsoup.connect(linkUrl);

        return Optional.of(connect.get())
                .map(document -> document.select("article").first())
                .map(element -> ArticleContent.builder()
                        .articleSeq(seq)
                        .contents(String.join("\n", element.select("p").eachText()))
                        .imageUrls(element.select("p > img").eachAttr("src"))
                        .build())
                .orElseThrow();
    }

    private Predicate<Article> filterNewArticle(LocalDate targetDate) {
        return article -> article.getCreatedAt().equals(targetDate);
    }
}
