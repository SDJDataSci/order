package com.order.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date convertString2Date(String target){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = formatter.parse(target);
            return date;
        } catch (ParseException e) {
            System.err.println("날짜 변환 실패: " + e.getMessage());
            return null;
        }
    }
}