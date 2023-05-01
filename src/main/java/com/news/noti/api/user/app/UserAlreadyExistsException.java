package com.news.noti.api.user.app;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("이미 존재하는 회원입니다");
    }
}
