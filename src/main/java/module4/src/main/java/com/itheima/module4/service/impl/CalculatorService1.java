package com.itheima.module4.service.impl;

import com.itheima.module4.dto.Calculator;
import com.itheima.module4.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;

/**
 * 业务逻辑类 - 等额本金方式
 */
@Service
public class CalculatorService1 implements CalculatorService {

    public CalculatorService1() {
        System.out.println("Service1 被创建了");
    }

    @Override
    public String[] cal(Calculator c) {
        double yr = c.getYr();
        int m = c.getM();
        double p = c.getP();

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

    @Override
    public String[][] details(Calculator c) {
        int m = c.getM();
        double yr = c.getYr();
        double p = c.getP();

        double payPrincipal = p / m;                        // 偿还本金
        double mr = yr / 12 / 100.0;
        String[][] a2 = new String[m][];
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                    // 偿还利息
            p -= payPrincipal;                              // 剩余本金
            double payment = payPrincipal + payInterest;    // 月供
            a2[i] = createRow(i, payment, payPrincipal, payInterest, p);
        }
        return a2;
    }

}
