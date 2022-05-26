package com.alicher;

import com.alicher.maths.FunctionLibrary;
import com.alicher.maths.Spline;
import com.alicher.maths.SplineOps;
import com.alicher.models.Function;
import com.alicher.util.GraphDisplay;
import com.alicher.util.UserInterface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Main {

    public static void main(String[] arg) throws IOException {
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
        try {
            userInteraction.displayMessage("Hi, choose one of the following functions:\n" + FunctionLibrary.functions[0].getStringFunc() + "\n" + FunctionLibrary.functions[1].getStringFunc() + "\n" + FunctionLibrary.functions[2].getStringFunc() + "\nManual input");
            int choice = userInteraction.readInt();
            Function function = null;
            int n;
            double[] x;
            double[] y;
            if (choice != 4) {
                function = FunctionLibrary.functions[choice - 1];
                userInteraction.displayMessage("Generate noise?");
                boolean noise = userInteraction.readBool();
                userInteraction.displayMessage("Please enter number of values");
                n = userInteraction.readInt();
                x = new double[n];
                userInteraction.displayMessage("Please enter X values");
                for (int i = 0; i < n; i++) {
                    x[i] = userInteraction.readDouble();
                }
                y = new double[n];
                if (!noise)
                    for (int i = 0; i < n; i++) {
                        y[i] = function.valueAtX(x[i]);
                    }
                else for (int i = 0; i < n; i++) {
                    Random random = new Random();
                    y[i] = function.valueAtX(x[i]) + random.nextDouble() * 0.1;
                }
            } else {
                userInteraction.displayMessage("Enter number of values");
                n = userInteraction.readInt();
                x = new double[n];
                y = new double[n];
                userInteraction.displayMessage("Enter function values");
                for (int i = 0; i < n; i++) {
                    x[i] = userInteraction.readDouble();
                    y[i] = userInteraction.readDouble();
                }
            }
            SplineOps splineOps = new SplineOps(x, y, n);
            Spline spline = splineOps.makeSpline();
//            System.out.println(spline.toString());
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
