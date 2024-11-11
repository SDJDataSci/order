package com.order.enums.Order1Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiExceptionEnum {
    SUCCESS(200, "외부 주문 전달 성공"),
    BAD_REQUEST(400, "올바르지 못한 응답"),
    UNAUTHORIZED(401, "권한 없음"),
    NOT_FOUND(404, "리소스 없음"),
    INTERNAL_SERVER_ERROR(500, "서버 내부 에러"),
    PARSING_ERROR(4001, "파싱 에러");

    private int code;
    private String message;

    ApiExceptionEnum(int statusCode, String message) {
        this.code = statusCode;
        this.message = message;
    }
}