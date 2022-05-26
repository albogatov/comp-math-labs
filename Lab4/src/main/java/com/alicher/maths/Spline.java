package com.alicher.maths;

import lombok.Data;

@Data
public class Spline {
    private double[] a;
    private double[] b;
    private double[] c;
    private double[] d;
    private double[] x;

    public Spline(double[] a, double[] b, double[] c, double[] d, double[] x) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.x = x;
    }

    public double getFuncValue(double x, int i) {
        return this.a[i] + this.b[i] * (x - this.x[i]) + this.c[i] * (x - this.x[i]) * (x - this.x[i]) + this.d[i] * (x - this.x[i]) * (x - this.x[i]) * (x - this.x[i]);
    }

    public String toString() {
        String toReturn = "";
        for (int i = 0; i < x.length; i++) {
            toReturn += a[i] + " " + b[i] + " " + c[i] + " " + d[i] + "\n";
        }
        return toReturn;
    }
}
