package com.news.noti.api.user.api;

import com.news.noti.api.common.api.ApiResponse;
import com.news.noti.api.user.app.UserAlreadyExistsException;
import com.news.noti.api.user.app.UserCreateRequest;
import com.news.noti.api.user.app.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCreateApi {
    private final UserCreateService userCreateService;

    @PostMapping("/users")
    public ApiResponse<Void> createUser(@RequestBody UserCreateRequest req) {
        try {
            userCreateService.join(req);

            return ApiResponse.ok();
        } catch (UserAlreadyExistsException e) {
            return ApiResponse.ok(e.getMessage());
        }
    }
}
