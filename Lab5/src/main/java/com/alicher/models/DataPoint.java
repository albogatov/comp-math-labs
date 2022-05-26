package com.alicher.models;

import lombok.Data;

@Data
public class DataPoint {
    private double x;
    private double y;

    public DataPoint() {

    }

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point: {" + "x = " + x + ", y = " + y + '}';
    }
}
