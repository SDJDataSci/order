package com.order.exceptions;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import com.order.enums.Order1Enum.ApiExceptionEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String message;
    private Map<String, Object> data;

    public ApiException(){
    }
    public ApiException(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = new HashMap<>();
    }
    public ApiException(HttpStatus httpStatus, ApiExceptionEnum apiExceptionEnum) {
        this.statusCode = apiExceptionEnum.getCode();
        this.message = apiExceptionEnum.getMessage();
        this.data = new HashMap<>();
    }
    public ApiException(HttpStatus httpStatus, ApiExceptionEnum apiExceptionEnum, String data) {
        this.statusCode = apiExceptionEnum.getCode();
        this.message = apiExceptionEnum.getMessage();
        this.data = new HashMap<>();
    }
}