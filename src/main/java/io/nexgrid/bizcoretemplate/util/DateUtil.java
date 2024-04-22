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

    // 전년도를 계산한다.
    public static String getPreviousYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1); // 현재 연도에서 1년을 빼준다.

        return formatter.format(calendar.getTime());
    }

    // 현재 달을 계산한다.
    public static String getMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");

        return formatter.format(new Date());
    }

    // 전월을 계산한다.
    public static String getPreviousMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 현재 월에서 1달을 빼준다.

        return formatter.format(calendar.getTime());
    }

    // 오늘 날짜를 계산한다.
    public static String getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        return formatter.format(calendar.getTime());
    }

    // 어제 날짜를 계산한다.
    public static String getYesterday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // 현재 날짜에서 1일을 빼준다.

        return formatter.format(calendar.getTime());
    }

    // 현재 시를 계산한다.
    public static String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Calendar calendar = Calendar.getInstance();

        return formatter.format(calendar.getTime());
    }

    // 현재 시간으로부터 1시간 전을 계산한다.
    public static String getOneHourAgo() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1); // 현재 시간에서 1시간을 빼준다.

        return formatter.format(calendar.getTime());
    }

    // 현재 시간을 계산한다.
    public static String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        Calendar calendar = Calendar.getInstance();

        return formatter.format(calendar.getTime());
    }

}