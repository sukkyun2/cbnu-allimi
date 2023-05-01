package com.news.noti.api.user.app;

import com.news.noti.api.user.domain.MemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCreateServiceTest {

    private UserCreateService userCreateService;

    @BeforeEach
    void setUp() {
        userCreateService = new UserCreateService(new MemoryUserRepository());
    }

    @Test
    @DisplayName("유저 생성시 토큰값이 없을경우 예외발생")
    void given_invalidate_req_then_throw_exception() {
        UserCreateRequest givenReq = new UserCreateRequest();

        assertThrows(IllegalArgumentException.class, () -> userCreateService.join(givenReq));
    }

    @Test
    @DisplayName("유저 생성시 이미 존재하는 유저일경우 예외발생")
    void given_exists_user_then_throw_exception() {
        String givenToken = "tdasd427yr2dg";
        userCreateService.join(new UserCreateRequest(givenToken));

        assertThrows(UserAlreadyExistsException.class, () -> userCreateService.join(new UserCreateRequest(givenToken)));
    }
}