package com.news.noti.api.user.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String token;

    public void validate() {
        Assert.notNull(token, "token is not null");
    }
}
