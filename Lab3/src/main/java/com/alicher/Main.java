package com.alicher;

import com.alicher.maths.FunctionLibrary;
import com.alicher.maths.Solver;
import com.alicher.models.Function;
import com.alicher.models.Solution;
import com.alicher.util.UserInterface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] arg) throws IOException {
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
        try {
            userInteraction.displayMessage("Hello! Please choose the integrated function among these templates:\n" + FunctionLibrary.functions[0].getStringFunc() + "\n" + FunctionLibrary.functions[1].getStringFunc() + "\n" + FunctionLibrary.functions[2].getStringFunc());
            Solver solver = new Solver();
            int choice = userInteraction.readInt();
            Function function = null;
            switch (choice) {
                case 1:
                    function = FunctionLibrary.functions[0];
                    break;
                case 2:
                    function = FunctionLibrary.functions[1];
                    break;
                case 3:
                    function = FunctionLibrary.functions[2];
                    break;
                default:
                    userInteraction.displayMessage("Wrong input, restart and try again");
                    System.exit(1);
            }
            userInteraction.displayMessage("Enter a");
            double a = userInteraction.readDouble();
            userInteraction.displayMessage("Enter b");
            double b = userInteraction.readDouble();
            userInteraction.displayMessage("Enter accuracy");
            double e = userInteraction.readDouble();
            ArrayList<Solution> solutions = solver.integrate(a, b, e, function);
            for (Solution s : solutions) {
                userInteraction.displayMessage(s.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
