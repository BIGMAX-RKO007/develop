package com.itheima.b;

import java.text.NumberFormat;
import java.util.Locale;

public class TestFormat {
    public static void main(String[] args) {
        String format = NumberFormat.getCurrencyInstance(Locale.KOREA).format(11991.010503055139);
        System.out.println(format);
    }
}
