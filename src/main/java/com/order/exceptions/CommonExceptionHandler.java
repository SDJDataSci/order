package com.order.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import com.order.dto.common.OrderCommonRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler({Exception.class})
    public OrderCommonRes handleException(Exception e) {
        e.printStackTrace();
        return OrderCommonRes.builder()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .message(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public OrderCommonRes handleMethodArgumentTypeMismatchException(Exception e) {
        return OrderCommonRes.builder()
                .statusCode(BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({ApiException.class})
    public OrderCommonRes handleApiException(ApiException e) {
        return OrderCommonRes.builder()
                .statusCode(e.getStatusCode())
                .message(e.getMessage())
                .data(e.getData())
                .build();
    }

}