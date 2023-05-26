package com.news.noti.collector.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "NT_CLT_HIST")
@NoArgsConstructor
public class CollectHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "COLLECT_TIME")
    private LocalDateTime collectTime;
    @Column(name = "COLLECT_STATUS")
    @Enumerated(EnumType.STRING)
    private CollectStatus collectStatus;

    public static CollectHistory fromStatus(CollectStatus status) {
        return new CollectHistory(status);
    }

    public CollectHistory(CollectStatus collectStatus) {
        this.collectTime = LocalDateTime.now();
        this.collectStatus = collectStatus;
    }
}
