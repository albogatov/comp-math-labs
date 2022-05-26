package com.alicher.maths;

import com.alicher.models.Function;
import com.alicher.models.Solution;

import java.util.ArrayList;

public class Solver {

    private final int MAX_ITER = 10000;

    public ArrayList<Solution> integrate(double a, double b, double e, Function function) {
        Solution leftSolution = executeMethod(a, b, e, function, 0);
        Solution midSolution = executeMethod(a, b, e, function, 0.5);
        Solution rightSolution = executeMethod(a, b, e, function, 1);
        leftSolution.setMethod("left");
        rightSolution.setMethod("right");
        midSolution.setMethod("middle");
        ArrayList<Solution> solutions = new ArrayList<>();
        solutions.add(leftSolution);
        solutions.add(midSolution);
        solutions.add(rightSolution);
        return solutions;
    }

    private double rectangleMethod(double a, double b, int n, double e, Function function, double k) {
        double h = (b - a) / n;
        double s = 0;
        a = a + k * h;
        for (int i = 0; i < n; i++) {
            double val = function.valueAtX(a + i * h);
            if (Double.isNaN(val)) {
                val = (function.valueAtX(a + i * h - e) + function.valueAtX(a + i * h + e))*0.5;
            }
            s += val;
        }
        s = h * s;
        return s;
    }

    private Solution executeMethod(double a, double b, double e, Function function, double k) {
        int n = 1;
        int iter = 1;
        double s1, s2;
        s2 = rectangleMethod(a, b, n, e, function, k);
        Solution solution = new Solution();
        do {
            s1 = s2;
            n *= 2;
            s2 = rectangleMethod(a, b, n, e, function, k);
            if (Double.isNaN(s2) || Double.isInfinite(s2)) {
                solution.setIsSolved("Error!");
                break;
            }
            iter++;
        } while (Math.abs(s1 - s2) > e && iter < MAX_ITER);
        if (iter >= MAX_ITER)
            solution.setIsSolved("Error!");
        solution.setAnswer(s2);
        solution.setParts(n);
        solution.setE(Math.abs(s1 - s2));
        return solution;
    }

}
