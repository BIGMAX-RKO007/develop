package com.itheima;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalDateTime - 年月日时分秒
 * LocalTime - 时分秒
 * LocalDate - 年月日
 */
public class TestDateTime {
    public static void main(String[] args) {
        LocalDateTime dt1 = LocalDateTime.of(2022, 7, 4, 10, 30, 22);

        LocalTime t1 = LocalTime.of(10, 30, 22);
        // 1. 计算   7.4 + 27 = 7.31    7.4 + 28 = 8.1
        System.out.println(dt1.plusDays(27));
        System.out.println(dt1.plusDays(28));
        System.out.println(dt1.minusDays(4));

        // 2. 获取当前时间
        System.out.println(LocalDateTime.now());
        System.out.println(LocalTime.now());

        // 3. 把字符串时间转化为对象时间
        String dateTime = "2022-04-25T12:30:00";
        String time = "00:06:28";

        LocalDateTime dt2 = LocalDateTime.parse(dateTime);
        System.out.println(dt2.getYear());
        System.out.println(dt2.getMonth());
        System.out.println(dt2.getMonthValue());
        System.out.println(dt2.getDayOfMonth());
        System.out.println(dt2.getHour());
        System.out.println(dt2.getMinute());
        System.out.println(dt2.getSecond());

        LocalTime t2 = LocalTime.parse(time);
        System.out.println(t2.getHour());
        System.out.println(t2.getMinute());
        System.out.println(t2.getSecond());
    }
}
