package com.alicher;

import com.alicher.maths.EquationLibrary;
import com.alicher.maths.Solver;
import com.alicher.models.Function;
import com.alicher.models.Solution;
import com.alicher.models.SystemSolution;
import com.alicher.util.UserInterface;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] arg) {
        try {
            UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
            userInteraction.displayMessage("Hello! Please enter 1 if you want to solve a non-linear equation" +
                    " or 2 if you want to solve a system of non-linear equations");
            Solver solver = new Solver();
            int choice = userInteraction.readInt();
            if (choice == 1) {
                userInteraction.displayMessage("Choose between these templates:\n" + EquationLibrary.equationTemplate1 + "\n" + EquationLibrary.equationTemplate2 + "\n" + EquationLibrary.equationTemplate3);
                choice = userInteraction.readInt();
                String function;
                userInteraction.displayMessage("Enter coefficients");
                double a = userInteraction.readDouble();
                double b = userInteraction.readDouble();
                double c;
                if (choice == 1) {
                    c = userInteraction.readDouble();
                    function = EquationLibrary.equation1(a, b, c);
                } else if (choice == 2) {
                    c = userInteraction.readDouble();
                    function = EquationLibrary.equation2(a, b, c);
                } else {
                    function = EquationLibrary.equation3(a, b);
                }
                userInteraction.displayMessage("Enter boundaries");
                double a_bound = userInteraction.readDouble();
                double b_bound = userInteraction.readDouble();
                userInteraction.displayMessage("Enter accuracy");
                double e = userInteraction.readDouble();
                Solution bisectionSolution = null;
                Solution chordSolution = null;
                try {
                    bisectionSolution = solver.solveByBisection(new Function(function), a_bound, b_bound, e);
                    userInteraction.displayMessage("Solution by bisection:\n" + bisectionSolution.toString());
                } catch (IllegalArgumentException ex) {
                    userInteraction.displayMessage(ex.getMessage());
                }
                try {
                    chordSolution = solver.solveByChord(new Function(function), a_bound, b_bound, e);
                    userInteraction.displayMessage("Solution by chord method:\n" + chordSolution.toString());
                } catch (Exception ex) {
                    userInteraction.displayMessage(ex.getMessage());
                }
                if (bisectionSolution != null && chordSolution != null)
                    userInteraction.displayMessage("The difference between results of methods is " + Math.abs(bisectionSolution.getVariable() - chordSolution.getVariable()));
            } else {
                userInteraction.displayMessage("Choose between these templates:\n" + EquationLibrary.systemTemplate1 + "\n\n" + EquationLibrary.systemTemplate2);
                choice = userInteraction.readInt();
                userInteraction.displayMessage("Enter coefficients");
                double a = userInteraction.readDouble();
                double b = userInteraction.readDouble();
                double c = userInteraction.readDouble();
                double d = userInteraction.readDouble();
                double e = userInteraction.readDouble();
                double f = userInteraction.readDouble();
                double g = userInteraction.readDouble();
                double h = 0;
                if (choice == 2)
                    h = userInteraction.readDouble();
                userInteraction.displayMessage("Enter accuracy");
                double eps = userInteraction.readDouble();
                String[] functionsString;
                if (choice == 1)
                    functionsString = EquationLibrary.getSystem1(a, b, c, d, e, f, g);
                else functionsString = EquationLibrary.getSystem2(a, b, c, d, e, f, g, h);
                Function[] functions = new Function[] {new Function(functionsString[0]), new Function(functionsString[1])};
                userInteraction.displayMessage("Enter intital approximations");
                double x_approx = userInteraction.readDouble();
                double y_approx = userInteraction.readDouble();
                SystemSolution systemSolution = solver.solveSystemNewton(x_approx, y_approx, functions, eps);
                userInteraction.displayMessage(systemSolution.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
