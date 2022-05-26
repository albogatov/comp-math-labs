package com.alicher.maths;

import com.alicher.models.DataPoint;
import com.alicher.models.Function;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Solver {

    public static final double ACCURACY = 1e-7;
    private final int MAX_ITER = 10000;
    private double H = 0.01;

    public List<DataPoint> applyMilneMethod(Function function, DataPoint initPoint, double end) {
        List<DataPoint> result = new ArrayList<>();
        result.add(initPoint);
        int i = 1;
        double initX = initPoint.getX() + H;
        for (double xi = initX; xi <= end; xi += H) {
            if (i <= 3) {
                result.add(getPointByRungeKutta(function, result.get(i - 1), xi));
            } else {
                result.add(getPointByMilne(function, result, xi, i));
            }
            i++;
        }
        return result;
    }

    private DataPoint getPointByRungeKutta(Function function, DataPoint prev, double x) {
        double[] k = new double[4];
        k[0] = H * function.valueAt(prev.getX(), prev.getY());
        k[1] = H * function.valueAt(prev.getX() + H * 0.5, prev.getY() + k[0] * 0.5);
        k[2] = H * function.valueAt(prev.getX() + H * 0.5, prev.getY() + k[1] * 0.5);
        k[3] = H * function.valueAt(prev.getX() + H, prev.getY() + k[2]);
        double delta = (k[0] + 2 * k[1] + 2 * k[2] + k[3]) / 6;
        return new DataPoint(x, prev.getY() + delta);
    }

    private DataPoint getPointByMilne(Function function, List<DataPoint> points, double xi, int i) {
        double forecast = 0, correction = 0;

        double y2 = points.get(i - 2).getY();
        double y4 = points.get(i - 4).getY();
        double f1 = function.valueAt(points.get(i-1).getX(), points.get(i-1).getY());
        double f2 = function.valueAt(points.get(i-2).getX(), points.get(i-2).getY());
        double f3 = function.valueAt(points.get(i-3).getX(), points.get(i-3).getY());

        correction =  y4 + 4*H/3 * (2*f3 - f2 + 2*f1);
//        xi += H;
        forecast = correction;
        while (Math.abs(correction - forecast) >= ACCURACY) {
            forecast = correction;
            correction = y2 + H/3 * (f2 - 4*f1 + 2*function.valueAt(xi, forecast));
        }
        return new DataPoint(xi, correction);
    }
}
