package com.alicher.models;

import lombok.Data;

@Data
public class Solution {
    private final double variable;
    private final int iters;

    public Solution(double variable, int iters) {
            this.variable = variable;
            this.iters = iters;
    }

    @Override
    public String toString() {
        return "Solution found is: " + variable + "\nThe solution was found in " + iters + " iterations";
    }
}
