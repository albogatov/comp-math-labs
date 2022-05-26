package com.alicher;

import com.alicher.maths.FunctionLibrary;
import com.alicher.maths.Solver;
import com.alicher.maths.Spline;
import com.alicher.maths.SplineOps;
import com.alicher.models.DataPoint;
import com.alicher.models.Function;
import com.alicher.util.GraphDisplay;
import com.alicher.util.UserInterface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] arg) throws IOException {
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
        Solver solver = new Solver();
        FunctionLibrary functionLibrary = new FunctionLibrary();
        try {
            userInteraction.displayMessage("Hello, please select a function");
            for (int i = 0; i < functionLibrary.getFunctions().size(); i++) {
                userInteraction.displayMessage(functionLibrary.getFunctions().get(i).toString());
            }
            int choice = userInteraction.readInt();
            Function function = functionLibrary.getFunctions().get(choice - 1);
            double x0 = 0;
            double y0 = 0;
            double end = 0;
            double h = 0.01;
            userInteraction.displayMessage("Enter x0");
            x0 = userInteraction.readDouble();
            userInteraction.displayMessage("Enter y0");
            y0 = userInteraction.readDouble();
            userInteraction.displayMessage("Enter border x value");
            end = userInteraction.readDouble();
            DataPoint initPoint = new DataPoint(x0, y0);
            List<DataPoint> solutionByMilneMethod = solver.applyMilneMethod(function, initPoint, end);
            int n = solutionByMilneMethod.size();
            double[] x = new double[n];
            double[] y = new double[n];
            userInteraction.displayMessage("Result points:");
            for (int i = 0; i < n; i++) {
                DataPoint current = solutionByMilneMethod.get(i);
                x[i] = current.getX();
                y[i] = current.getY();
                userInteraction.displayMessage(String.format("( %018.9f : %018.9f )\n",  x[i], y[i]));
            }
            SplineOps splineOps = new SplineOps(x, y, n);
            Spline spline = splineOps.makeSpline();
            GraphDisplay graphDisplay;
            if (function != null)
                graphDisplay = new GraphDisplay(spline, function.getStringFunc(), null);
            else graphDisplay = new GraphDisplay(spline, null, y);
            graphDisplay.draw(function);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
