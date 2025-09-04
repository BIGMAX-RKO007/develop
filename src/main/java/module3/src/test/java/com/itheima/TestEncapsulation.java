package com.itheima;

import com.itheima.encapsulation.Car;

/**
 * encapsulation 封装
 * 尽可能让访问范围更小
 *      private < 默认 < protected < public
 *      尤其是字段 建议设置为 private
 *      想让子类用 考虑设置为 protected
 */
public class TestEncapsulation {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.y);
        car.test();
    }
}
