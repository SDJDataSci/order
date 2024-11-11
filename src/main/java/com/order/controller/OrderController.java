package com.order.controller;

import com.order.dto.common.OrderCommonRes;
import com.order.dto.req.OrderReq;
import com.order.dto.res.OrderRes;
import com.order.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/order/get")
    public OrderCommonRes orderInfoGet(@RequestBody OrderReq orderReq) throws Exception {

        OrderRes getOrder = orderService.getOrder(orderReq);

        return OrderCommonRes.builder()
                .statusCode(OK.value())
                .message("정상적으로 처리되었습니다.")
                .data(getOrder)
                .build();
    }

    @PostMapping("/api/order/create")
    public ResponseEntity<String> orderCreate(@RequestBody OrderReq orderReq) throws Exception {

        orderService.createOrder(orderReq);

        return ResponseEntity.ok("전송 성공");
    }
}