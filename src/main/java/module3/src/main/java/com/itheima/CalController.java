package com.itheima;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.NumberFormat;

// 改造前
@Controller
public class CalController {
    
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
        if (type != 0 && type != 1 && type != 2) {
            throw new IllegalArgumentException("不支持的还款类型");
        }
    }

    Calculator[] getCalculator(double p, int m, double yr) {
        return new Calculator[]{
                new Calculator0(p, m, yr), //0
                new Calculator1(p, m, yr), //1
                new Calculator2(p, m, yr)
        };
    }

    @RequestMapping("/cal")
    @ResponseBody
    String[] cal(double p, int m, double yr, int type) { // 0 1 2
        check(p, m, yr, type);
        Calculator[] calculators = getCalculator(p, m, yr);
        Calculator c = calculators[type];// 通过 type 获取对应的子类对象
        return c.cal();
    }

    @RequestMapping("/details")
    @ResponseBody
    String[][] details(double p, int m, double yr, int type) {
        check(p, m, yr, type);
        Calculator[] calculators = getCalculator(p, m, yr);
        Calculator c = calculators[type];
        return c.details();
    }

}
