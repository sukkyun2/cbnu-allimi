package com.news.noti.notifier;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class FCMPushSendResponse {
    private Long multicast_id;
    private Integer success;
    private Integer failure;
    private Integer canonical_ids;
    private List<Map<String, String>> results;

    public boolean isOk() {
        return failure == null || failure == 0;
    }
}
