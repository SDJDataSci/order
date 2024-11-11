package com.order.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderReq {
    String orderId;
    String customerName;
    Date orderDate;
    String orderStep;
}