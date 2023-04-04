package com.news.noti.notifier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMPushNotifier {
    private final FCMPushClient pushClient;

    public void sendNotification(FCMPushSendRequest req){
        FCMPushSendResponse response = pushClient.sendPushMessage(req);

        if(!response.isOk()){
            throw new NotifyException();
        }
    }
}
