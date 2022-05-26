package com.alicher.models;

import lombok.Data;

@Data
public class SystemSolution {
    private final double x;
    private final double y;
    private final int iters;

    public SystemSolution(double x, double y, int iters) {
        this.x = x;
        this.y = y;
        this.iters = iters;
    }

    @Override
    public String toString() {
        return "Solution found for x is: " + x + "\nFor y: " + y + "\nThe solution was found in " + iters + " iterations";
    }
}
