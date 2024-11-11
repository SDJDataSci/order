package com.order.dto.res;

import com.order.enums.Order1Enum.OrderStep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderRes {

    String orderId;
    String customerName;
    Date orderDate;
    String orderStep;
}