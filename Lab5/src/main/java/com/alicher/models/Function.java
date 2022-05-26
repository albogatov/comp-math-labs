package com.alicher.models;

import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Data
public class Function {
    private Expression function;
    private String stringFunc;

    public Function(String function, String... vars) {
        this.stringFunc = function;
        this.function = new ExpressionBuilder(function).variables(vars).build();
    }

    public double valueAt(double... vals) {
        try {
            int idx = 0;
            for (String var : function.getVariableNames()) {
                this.function.setVariable(var, vals[idx++]);
            }
            return this.function.evaluate();
        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }

    @Override
    public String toString() {
        return stringFunc;
    }
}
