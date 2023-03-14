package com.news.noti.scraper;

import lombok.RequiredArgsConstructor;
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
public class CBNUSoftwareArticleScraper {
    private final String cbnuSoftwareUrl;

    public CBNUSoftwareArticleScraper(@Value("${cbnusoftware.url}") String cbnuSoftwareUrl) {
        this.cbnuSoftwareUrl = cbnuSoftwareUrl;
    }

    public List<CBNUSoftwareArticle> scrap(LocalDate targetDate) {
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

    private CBNUSoftwareArticle convert(Element element) {
        String href = element.selectFirst(".title > a").attr("href");

        return CBNUSoftwareArticle.builder()
                .seq(Integer.valueOf(element.getElementsByClass("m_no").first().text()))
                .categoryName(element.getElementsByClass("cate").first().text())
                .title(element.select(".title > a").first().text())
                .linkUrl(href)
                .content(getContent(href))
                .createdAt(LocalDate.parse(element.getElementsByClass("time").first().text(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }

    @SneakyThrows
    private CBNUSoftwareArticleContent getContent(String linkUrl) {
        Connection connect = Jsoup.connect(linkUrl);

        return Optional.of(connect.get())
                .map(document -> document.select("article").first())
                .map(element -> CBNUSoftwareArticleContent.builder()
                        .contents(String.join("\n", element.select("p").eachText()))
                        .imageUrls(element.select("p > img").eachAttr("src"))
                        .build())
                .orElseThrow();
    }

    private Predicate<CBNUSoftwareArticle> filterNewArticle(LocalDate targetDate){
        return article -> article.getCreatedAt().equals(targetDate);
    }
}
