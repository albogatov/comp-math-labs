package com.alicher.maths;

import com.alicher.models.Function;
import com.alicher.models.Solution;
import com.alicher.models.SystemSolution;

import static java.lang.Float.NaN;

public class Solver {

    private final int MAX_ITERS = 1000000;


    public Solution solveByBisection(Function function, double a, double b, double e) {
           if (function.valueAtX(a)*function.valueAtX(b) > 0) {
               throw new IllegalArgumentException("Function does not fit for this method");
           } else {
               int iters = 0;
               double variable = 0;
               do {
                   variable = (a+b)/2;
                   if (function.valueAtX(a)*function.valueAtX(variable) > 0)
                       a = variable;
                   else b = variable;
                   iters++;
               } while (Math.abs(b - a) > 2*e && iters <= MAX_ITERS);
               return new Solution(variable, iters);
           }
    }

    public Solution solveByChord(Function function, double a, double b, double e) {
        double xNext = 0;
        double buffer;
        int iters = 0;
        if (function.valueAtX(a)*function.valueAtX(b) > 0) {
            throw new IllegalArgumentException("Function does not fit for this method");
        }
        do {
            buffer = xNext;
            xNext = b - function.valueAtX(b)*(a - b) / (function.valueAtX(a) - function.valueAtX(b));
            a = b;
            b = buffer;
            iters++;
        } while (Math.abs(xNext - b) > e);
        if (iters == MAX_ITERS || xNext == NaN)
            throw new ArithmeticException("Accuracy not reached");
        return new Solution(xNext, iters);
    }

    public double findJacobian(Function[] functions, double x, double y) {
        return functions[0].getDerivativeX(x, y, 1e-9) * functions[1].getDerivativeY(x, y, 1e-9) - functions[1].getDerivativeX(x, y, 1e-9) * functions[0].getDerivativeY(x, y, 1e-9);
    }

    public double dX(Function[] functions, double x, double y) {
        return functions[0].valueAtXY(x, y) * functions[1].getDerivativeY(x, y, 1e-9) - functions[1].valueAtXY(x, y) * functions[0].getDerivativeY(x, y, 1e-9);
    }

    public double dY(Function[] functions, double x, double y) {
        return functions[1].valueAtXY(x, y) * functions[0].getDerivativeX(x, y, 1e-9) - functions[0].valueAtXY(x, y) * functions[1].getDerivativeX(x, y, 1e-9);
    }

    public SystemSolution solveSystemNewton(double x, double y, Function[] functions, double e) {
        if (functions.length > 2)
            throw new IllegalArgumentException("Method not applicable!");
        else {
            double delta = 1;
            double xPrev = 0;
            double yPrev = 0;
            int iters = 0;
            do {
                xPrev = x;
                yPrev = y;
                x = xPrev - dX(functions, xPrev, yPrev) / findJacobian(functions, xPrev, yPrev);
                y = yPrev - dY(functions, xPrev, yPrev) / findJacobian(functions, xPrev, yPrev);
                if (Math.abs(x - xPrev) > Math.abs(y - yPrev))
                    delta = Math.abs(x - xPrev);
                else delta = Math.abs(y - yPrev);
                iters++;
            } while (delta > e && iters < MAX_ITERS);
            if (iters == MAX_ITERS || x == NaN || y == NaN)
                throw new ArithmeticException("Accuracy not reached");
            else return new SystemSolution(x, y, iters);
        }
    }
}
