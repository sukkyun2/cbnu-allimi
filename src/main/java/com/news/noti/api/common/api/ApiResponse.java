package com.news.noti.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;


    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder().code("200").message("OK").data(data).build();
    }

    public static <T> ApiResponse<T> ok(String message) {
        return ApiResponse.<T>builder().code("200").message(message).build();
    }

    public static <T> ApiResponse<T> ok() {
        return ApiResponse.<T>builder().code("200").message("OK").build();
    }

    public static <T> ApiResponse<T> badRequest() {
        return ApiResponse.<T>builder().code("400").message("잘못된 요청입니다").build();
    }

    public static <T> ApiResponse<T> failWithMessage(String message) {
        return ApiResponse.<T>builder()
                .code("500")
                .message(message)
                .build();
    }
}