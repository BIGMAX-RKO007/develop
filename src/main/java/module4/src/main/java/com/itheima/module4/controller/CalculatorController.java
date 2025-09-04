package com.itheima.module4.controller;

import com.itheima.module4.dto.Calculator;
import com.itheima.module4.service.CalculatorService;
import com.itheima.module4.service.impl.CalculatorService0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 改造后
@Controller // 控制器对象只有一个
public class CalculatorController {

    /*private CalculatorService[] array = new CalculatorService[] {
            new CalculatorService0(),
            new CalculatorService1(),
            new CalculatorService2()
    };*/

    @Autowired
    CalculatorService[] array; // = new 数组[]{Service0 对象,Service1 对象,Service2 对象}

    @Autowired
    CalculatorService0 s0; // = CalculatorService0 对象

    // 还款总额
    @RequestMapping("/cal")
    @ResponseBody
    /*
        /cal?p=100000.0&m=10&yr=6.0&type=0

        情况1：有无参构造
        Calculator c = new Calculator();
        c.setP(100000.0);
        c.setM(10);
        c.setYr(6.0);

        情况2：没有无参构造
        Calculator c = new Calculator(100000.0, 10, 6.0);

        cal(0, c)
     */
    String[] cal(int type, Calculator c) { // 0 1 2
        check(c.getP(), c.getM(), c.getYr(), type);
        CalculatorService service = array[type];
        return service.cal(c);
    }

    // 还款详情
    @RequestMapping("/details")
    @ResponseBody
    String[][] details(int type, Calculator c) {
        check(c.getP(), c.getM(), c.getYr(), type);
        CalculatorService service = array[type];
        return service.details(c);
    }

    // 检查参数的有效性
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
}
