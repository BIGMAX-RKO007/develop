package com.itheima;

/**
 * 测试对象演化
 */
public class TestCal {
    public static void main(String[] args) {
        // 对比相同本金，不同利率和贷款月份，计算总还款额(等额本息)，看哪个更划算一些
//        double p1 = 100000.0;
//        int m1 = 24;
//        double yr1 = 4.5;
//        double r1 = cal(p1, m1, yr1);
        Cal c1 = new Cal(100000.0, 24, 4.5);
        double r1 = c1.cal();
        System.out.println("4.5% 利率借 2 年：" + r1);

//        double p2 = 100000.0;
//        int m2 = 12;    // 1 年
//        double yr2 = 6.0;
//        double r2 = cal(p2, m2, yr2);
        Cal c2 = new Cal(100000.0, 12, 6.0);
        double r2 = c2.cal();
        System.out.println("6.0% 利率借 1 年：" + r2);
    }

    /*static double cal(double p, int m, double yr) {
        double mr = yr / 100.0 / 12;
        double pow = Math.pow(1 + mr, m);
        return m * p * mr * pow / (pow - 1);
    }*/
}
