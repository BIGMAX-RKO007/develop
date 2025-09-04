package com.itheima.module4.dto;

import java.io.Serializable;

// data transfer object (数据传输对象)
// Java Bean - 存数据
public class Calculator implements Serializable {
    private double p;
    private int m;
    private double yr;

    public Calculator(double p, int m, double yr) {
        this.p = p;
        this.m = m;
        this.yr = yr;
    }

    /*public Calculator() {
    }*/

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getYr() {
        return yr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }
}
