package com.order.enums.Order1Enum;

public enum OrderStep {
    주문접수("0001", "주문접수"),
    입금확인("0002", "입금확인"),
    배송준비("0003", "배송준비"),
    배송중("0004", "배송중"),
    배송완료("0005", "배송완료"),
    구매확정("0006", "구매확정"),
    주문취소("0100", "주문취소"),
    환불완료("0101", "환불완료"),
    반품준비("0102", "반품준비"),
    반품완료("0103", "반품완료");

    private final String code;
    private final String description;

    OrderStep(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStep fromCode(String code) {
        for (OrderStep step : values()) {
            if (step.getCode().equals(code)) {
                return step;
            }
        }
        throw new IllegalArgumentException("장애 코드: " + code);
    }

    public static String fromDescription(String description) {
        for (OrderStep step : OrderStep.values()) {
            if (step.getDescription().equals(description)) {
                return step.getCode();
            }
        }
        throw new IllegalArgumentException("No matching OrderStep for description: " + description);
    }
}