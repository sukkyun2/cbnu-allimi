package com.news.noti.notifier;

import com.news.noti.config.FCMFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "fcm-client", url = "${fcm.api.url}", configuration = FCMFeignConfiguration.class)
public interface FCMPushClient {
    @PostMapping("/fcm/send")
    FCMPushSendResponse sendPushMessage(FCMPushSendRequest req);

}
