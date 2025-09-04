package com.itheima;

public class Cal {

    double p;
    int m;
    double yr;

    public Cal(double p, int m, double yr) {
        this.p = p;
        this.m = m;
        this.yr = yr;
    }

    double cal() {
        double mr = yr / 100.0 / 12;
        double pow = Math.pow(1 + mr, m);
        return m * p * mr * pow / (pow - 1);
    }
}
