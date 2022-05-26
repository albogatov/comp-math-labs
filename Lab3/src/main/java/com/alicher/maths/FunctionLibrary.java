package com.alicher.maths;

import com.alicher.models.Function;

import java.lang.Math.*;

public class FunctionLibrary {

    public static final Function[] functions = new Function[]{
            new Function((x) -> Math.sin(x) / x,
                    "sin(x) / x"),
            new Function((x) -> 1/x,
                    "1/x"),
            new Function((x) -> Math.pow(x,2)*Math.sin(x)/10,
                    "x^2*sin(x)/10")
    };

}
