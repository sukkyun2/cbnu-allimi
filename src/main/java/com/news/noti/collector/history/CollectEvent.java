package com.news.noti.collector.history;

import com.news.noti.api.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.news.noti.collector.history.CollectStatus.*;

@Getter
@AllArgsConstructor
public class CollectEvent {
    private List<Article> articles;

    public CollectStatus getCollectStatus() {
        return CollectionUtils.isEmpty(articles) ? NODATA : SUCCESS;
    }
}
