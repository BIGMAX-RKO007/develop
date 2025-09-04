package com.itheima.module4.service.impl;

import com.itheima.module4.dto.Calculator;
import com.itheima.module4.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;

/**
 * 业务逻辑类 - 等额本息方式
 */
@Service
public class CalculatorService0 implements CalculatorService {

    public CalculatorService0() {
        System.out.println("Service0 被创建了");
    }

    @Override
    public String[] cal(Calculator c) {
        double yr = c.getYr();
        int m = c.getM();
        double p = c.getP();
        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);
        return new String[]{
                NumberFormat.getCurrencyInstance().format(payment * m),
                NumberFormat.getCurrencyInstance().format(payment * m - p)
        };
    }

    @Override
    public String[][] details(Calculator c) {
        int m = c.getM();
        double yr = c.getYr();
        double p = c.getP();
        String[][] a2 = new String[m][];
        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);              // 月供
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                        // 偿还利息
            double payPrincipal = payment - payInterest;        // 偿还本金
            p -= payPrincipal;                                  // 剩余本金

            a2[i] = createRow(i, payment, payPrincipal, payInterest, p);
        }
        return a2;
    }
}
