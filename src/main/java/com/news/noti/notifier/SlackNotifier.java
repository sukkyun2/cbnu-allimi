package com.news.noti.notifier;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SlackNotifier {
    private final Slack slack;
    private final String webhookUrl;

    public SlackNotifier(@Value("${slackwebhook.url}")String webhookUrl) {
        this.slack = Slack.getInstance();
        this.webhookUrl = webhookUrl;
    }

    public void sendNotification(SlackNotifyRequest req) {
        try {
            WebhookResponse response = slack.send(webhookUrl, req.getPayload());

            if(response.getCode() != 200){
                throw new NotifyException();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new NotifyException();
        }
    }

}
