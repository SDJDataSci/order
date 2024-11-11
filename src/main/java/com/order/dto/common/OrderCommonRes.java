package com.order.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderCommonRes {
    private int statusCode;
    private String message;
    private Object data;

    @Builder
    OrderCommonRes(int statusCode, String message, Object data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}