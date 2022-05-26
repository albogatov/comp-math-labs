package com.alicher.maths;

import com.alicher.models.Function;

import java.lang.Math.*;

public class FunctionLibrary {

    public static final Function[] functions = new Function[]{
            new Function((x) -> Math.sin(x),
                    "sin(x)"),
            new Function((x) -> Math.cos(x),
                    "cos(x)"),
            new Function((x) -> Math.pow(Math.E, x),
                    "e^x")
    };

}
