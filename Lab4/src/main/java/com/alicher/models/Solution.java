package com.alicher.models;

import lombok.Data;

@Data
public class Solution {
    private double answer;
    private double e;
    private int parts;
    private String isSolved = "";
    private String method = "";

    public Solution() {

    }

    @Override
    public String toString() {
        if (isSolved.equals(""))
            return "Solution found by " + method + " rectangles method is: " + answer + "\nNumber of parts is: " + parts +"\nError is: " + e;
        else return "Solution not found, the " + method + " rectangles method is inapplicable";
    }
}
