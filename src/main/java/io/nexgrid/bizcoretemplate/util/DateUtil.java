package io.nexgrid.bizcoretemplate.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    // 현재 연도를 계산한다.
    public static String getYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        return formatter.format(new Date());
    }

    // 현재 달을 계산한다.
    public static String getMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");

        return formatter.format(new Date());
    }

    // 오늘 날짜를 계산한다.
    public static String getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        return formatter.format(calendar.getTime());
    }

    // 현재 시를 계산한다.
    public static String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh");
        Calendar calendar = Calendar.getInstance();

        return formatter.format(calendar.getTime());
    }

}
