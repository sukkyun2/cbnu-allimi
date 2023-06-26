package com.news.noti.notifier;

import com.news.noti.api.article.domain.Article;
import com.news.noti.api.article.domain.ArticleContent;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.webhook.Payload;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlackNotifyRequestTest {

    @Test
    void when_article_empty_then_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> new SlackNotifyRequest(Collections.emptyList()));
    }

    @Test
    void slack_payload_test() {
        SlackNotifyRequest req = new SlackNotifyRequest(givenArticles());

        Payload payload = req.getPayload();

        List<LayoutBlock> blocks = payload.getBlocks();
        assertThat(blocks).hasSize(3);
        assertThat(((SectionBlock) blocks.get(0)).getText().getText()).isEqualTo("총 1개의 소식");
        assertThat(((SectionBlock) blocks.get(2)).getText().getText()).isEqualTo("2023학년도 2학기 영어권 SAF 자매대학 파견 교환학생 선발 안내");
        assertThat(((ButtonElement) ((SectionBlock) blocks.get(2)).getAccessory()).getUrl()).isEqualTo("https://software.cbnu.ac.kr/sub0401/14841");
    }

    List<Article> givenArticles() {
        return List.of(Article.builder()
                .seq(13)
                .categoryName("학부")
                .title("2023학년도 2학기 영어권 SAF 자매대학 파견 교환학생 선발 안내")
                .linkUrl("https://software.cbnu.ac.kr/sub0401/14841")
                .createdAt(LocalDate.now())
                .content(ArticleContent.builder()
                        .imageUrls(Collections.emptyList())
                        .contents("공지내용")
                        .build())
                .build());
    }

}