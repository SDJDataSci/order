package com.order.service.interfaces;

import com.order.dto.common.OrderCommonRes;
import com.order.dto.req.OrderReq;
import com.order.dto.res.OrderRes;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public interface OrderService {
    // 주문 취득
    OrderRes getOrder(OrderReq orderReq) throws Exception ;

    // 주문 요청
    void createOrder(OrderReq orderReq) throws Exception ;

    // 주문 삭제
    HttpHeaders getHeaders();

    RestTemplate getRestTemplate();
}
