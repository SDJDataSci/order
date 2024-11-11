package com.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.config.ExternalApiConfig;
import com.order.dto.req.OrderReq;
import com.order.dto.res.OrderRes;
import com.order.enums.Order1Enum.ApiExceptionEnum;
import com.order.enums.Order1Enum.OrderStep;
import com.order.exceptions.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URLDecoder;
import java.util.*;

import com.order.service.interfaces.OrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static com.order.utils.DateUtil.convertString2Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    List<OrderReq> failedOrderList = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ExternalApiConfig externalConfig;

    RestTemplate restTemplate = getRestTemplate();


    @Override
    public OrderRes getOrder(OrderReq orderReq) {
        final String EXTERNAL_INFOURL = externalConfig.getInfoUrl();

        Map<String, Object> parameters = new HashMap<>();
        HttpEntity httpEntity = new HttpEntity<>(parameters, getHeaders());
        ResponseEntity<String> result = null;
        log.info("[READY GETORDER] Parameters: " + parameters);
        result = restTemplate.postForEntity(EXTERNAL_INFOURL, httpEntity, String.class);
        log.info("[SUCCESS GETORDER] ID: " + orderReq.getOrderId());
        JSONArray resultJson = null;
        resultJson = convertString2Json(result);
        OrderRes orderRes = convertJson2List(resultJson);
        return orderRes;
    }


    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));
        return headers;
    }
    @Override
    @Transactional
    public void createOrder(OrderReq orderReq) throws Exception  {
        final String EXTERNAL_URL = externalConfig.getCreateUrl();

        try{
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("orderId", orderReq.getOrderId());
            parameters.put("customerName", orderReq.getCustomerName());
            parameters.put("orderDate", orderReq.getOrderDate());
            parameters.put("orderStep", orderReq.getOrderStep());

            HttpEntity httpEntity = new HttpEntity<>(parameters, getHeaders());
            ResponseEntity<String> result = null;
            log.info("[READY CREATEORDER] Parameters: " + parameters);
            result = restTemplate.postForEntity(EXTERNAL_URL, httpEntity, String.class);
            log.info("[SUCCESS CREATEORDER] ID: " + orderReq.getOrderId());
        } catch (Exception e) {
            log.error("[FAIL CREATEORDER] ID: " + orderReq.getOrderId());
            log.error("[FAIL CREATEORDER REASON] " + e.getMessage());
            failedOrderList.add(orderReq);
        }
    }
    @Override
    public RestTemplate getRestTemplate()  {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10*1000);
        factory.setConnectionRequestTimeout(10*1000);
        return new RestTemplate(factory);
    }

    private JSONArray convertString2Json(ResponseEntity<String> result) {
        try {
            String decodeResult = URLDecoder.decode(result.getBody(), "UTF-8");
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(decodeResult);
        } catch (Exception e) {
            log.error("Error during JSON parsing or decoding: ", e);
            throw new ApiException(HttpStatus.BAD_REQUEST, ApiExceptionEnum.PARSING_ERROR, e.getMessage());
        }
    }

    private OrderRes convertJson2List(JSONArray jsonArray) {
        OrderRes orderRes = new OrderRes();
        for (Object obj : jsonArray) {
            JSONObject jObj = (JSONObject) obj;
            orderRes.setOrderId((String) jObj.get("orderId"));
            orderRes.setCustomerName((String) jObj.get("customerName"));
            orderRes.setOrderDate(convertString2Date((String) jObj.get("orderDate")));
            orderRes.setOrderStep(OrderStep.fromDescription((String) jObj.get("orderStep")));
        }
        return orderRes;
    }
}