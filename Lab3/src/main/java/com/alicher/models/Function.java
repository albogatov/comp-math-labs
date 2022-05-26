package com.alicher.models;

import lombok.Data;

@Data
public class Function {
    private MathExpr function;
    private String stringFunc;

    public Function(MathExpr function, String stringFunc) {
        this.function = function;
        this.stringFunc = stringFunc;
    }

    public double valueAtX(double x) {
        return function.valueAtX(x);
    }
}
