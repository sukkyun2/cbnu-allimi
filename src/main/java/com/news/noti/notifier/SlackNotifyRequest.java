package com.news.noti.notifier;

import com.news.noti.api.article.domain.Article;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.webhook.Payload;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.button;
import static com.slack.api.webhook.WebhookPayloads.payload;

public class SlackNotifyRequest {
    private List<Article> articles;

    public SlackNotifyRequest(List<Article> articles) {
        if(CollectionUtils.isEmpty(articles)){
            throw new IllegalArgumentException("article size should be greater than 0");
        }

        this.articles = articles;
    }

    public Payload getPayload(){
        return payload(p -> p.blocks((List<LayoutBlock>) Stream.of(Blocks.asBlocks(getHeader(),divider()),getSections())
                .flatMap(Collection::stream)
                .toList()));
    }

    private SectionBlock getHeader(){
        return section(section -> section.text(plainText(MessageFormat.format("총 {0}개의 소식", articles.size()))));
    }

    private List<SectionBlock> getSections(){
        return articles.stream()
                .map(article-> section(section -> section.text(markdownText(article.getTitle()))
                        .accessory(button(b->b.text(plainText("click")).url(article.getLinkUrl()).actionId("button-action")))))
                .toList();
    }
}
