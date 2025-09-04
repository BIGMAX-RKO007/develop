package com.itheima.module2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.NumberFormat;

@Controller
public class CalController {

    /**
     * 以等额本息法计算还款总额和总利息
     *
     * @param p  本金
     * @param m  月份
     * @param yr 年利率
     * @return 字符串数组 [0]还款总额 [1]总利息
     */
    static String[] cal0(double p, int m, double yr) {
        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);
//        return NumberFormat.getCurrencyInstance().format(payment * m);
        return new String[]{
                NumberFormat.getCurrencyInstance().format(payment * m),
                NumberFormat.getCurrencyInstance().format(payment * m - p)
        };
    }

    static String[] cal1(double p, int m, double yr) {
        double payPrincipal = p / m;        // 偿还本金
        double backup = p;                  // 备份本金
        double mr = yr / 12 / 100.0;
        double payInterestTotal = 0.0;      // 总利息
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;    // 偿还利息
            p -= payPrincipal;              // 剩余本金
            payInterestTotal += payInterest;
        }
        // [0]还款总额   [1]总利息
        return new String[]{
                NumberFormat.getCurrencyInstance().format(backup + payInterestTotal),
                NumberFormat.getCurrencyInstance().format(payInterestTotal)
        };
    }

    static void check(double p, int m, double yr, int type) {
        if (p <= 0) {
            throw new IllegalArgumentException("贷款金额必须>0");
        }
        if (m < 1 || m > 360) {
            throw new IllegalArgumentException("贷款月份必须在 1~360 之间");
        }
        if (yr < 1.0 || yr > 36.0) {
            throw new IllegalArgumentException("年利率必须在 1~36 之间");
        }
        if (type != 0 && type != 1) {
            throw new IllegalArgumentException("不支持的还款类型");
        }
    }

    @RequestMapping("/cal")
    @ResponseBody
    String[] cal(double p, int m, double yr, int type) {
        check(p, m, yr, type);
        if (type == 0) { // 等额本息
            return cal0(p, m, yr);
        } else {    // 等额本金
            return cal1(p, m, yr);
        }
    }

    @RequestMapping("/details")
    @ResponseBody
    String[][] details(double p, int m, double yr, int type) {
        check(p, m, yr, type);
        if (type == 0) {
            return details0(p, m, yr);
        } else {
            return details1(p, m, yr);
        }

    }

    static String[][] details1(double p, int m, double yr) {
        // 等额本金
        double payPrincipal = p / m;                        // 偿还本金
        double mr = yr / 12 / 100.0;
        String[][] a2 = new String[m][];
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                    // 偿还利息
            p -= payPrincipal;                              // 剩余本金
            double payment = payPrincipal + payInterest;    // 月供
            String[] row = new String[]{
                    (i + 1) + "",
                    NumberFormat.getCurrencyInstance().format(payment),
                    NumberFormat.getCurrencyInstance().format(payPrincipal),
                    NumberFormat.getCurrencyInstance().format(payInterest),
                    NumberFormat.getCurrencyInstance().format(p)
            };
            a2[i] = row;
        }
        return a2;
    }

    static String[][] details0(double p, int m, double yr) {
        //        String[] row0 = new String[]{"1", "¥33,667.22", "¥33,167.22", "¥500.00", "¥66,832.78"};
//        String[] row1 = new String[]{"2", "¥33,667.22", "¥33,333.06", "¥334.16", "¥33,499.72"};
//        String[] row2 = new String[]{"3", "¥33,667.22", "¥33,499.72", "¥167.50", "¥0.00"};

        // String[][] 变量名 = new String[外层长度][内层长度];
        String[][] a2 = new String[m][];
//        a2[0] = row0;
//        a2[1] = row1;
//        a2[2] = row2;

        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);              // 月供
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                        // 偿还利息
            double payPrincipal = payment - payInterest;        // 偿还本金
            p -= payPrincipal;                                  // 剩余本金
            String[] row = new String[]{                       // 一行的数据
                    (i + 1) + "",
                    NumberFormat.getCurrencyInstance().format(payment),
                    NumberFormat.getCurrencyInstance().format(payPrincipal),
                    NumberFormat.getCurrencyInstance().format(payInterest),
                    NumberFormat.getCurrencyInstance().format(p)
            };
            a2[i] = row;
        }
        return a2;
    }

}
