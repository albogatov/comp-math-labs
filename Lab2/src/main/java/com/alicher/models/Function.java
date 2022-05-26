package com.alicher.models;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {

    private Expression function;

    public Function(String input) {
        this.function = new ExpressionBuilder(input).variables("x", "y").build();
    }

    public double valueAtX(double x) {
        return function.setVariable("x", x).evaluate();
    }

    public double valueAtXY(double x, double y) {
        return function.setVariable("x", x).setVariable("y", y).evaluate();
    }

    public double getDerivativeX(double x, double y, double d) {
        return (this.valueAtXY(x + d, y) - this.valueAtXY(x - d, y)) / (2 * d);
    }

    public double getDerivativeY(double x, double y, double d) {
        return (this.valueAtXY(x, y + d) - this.valueAtXY(x, y - d)) / (2 * d);
    }

}
