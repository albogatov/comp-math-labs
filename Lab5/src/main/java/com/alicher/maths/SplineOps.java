package com.alicher.maths;

import com.alicher.models.Matrix;

public class SplineOps {
    double[] x;
    double[] y;
    int n;

    public SplineOps(double[] x, double y[], int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public Spline makeSpline() {
        double[] hs = getHArray(x);
        double[][] cs = getSplineMatrix(hs, y);
        Matrix matrix = new Matrix(y.length, cs);
        SystemSolver systemSolver = new SystemSolver();
        Matrix solvedMatrix = systemSolver.findTriangularMatrix(matrix);
        double[] result = systemSolver.solveSystem(solvedMatrix);
        double[] bs = getBArray(hs, result);
        double[] ds = getDArray(hs, result);
        return new Spline(this.y, bs, result, ds, this.x);
    }

    private double[] getHArray(double[] x) {
        double[] result = new double[x.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = x[i + 1] - x[i];
        }
        return result;
    }

    private double[] getBArray(double[] hs, double[] cs) {
        double[] bs = new double[y.length];
        for (int i = 1; i < bs.length; i++)
            bs[i] = (y[i] - y[i - 1]) / hs[i - 1] + hs[i - 1] * (cs[i] * 2 / 3 + cs[i - 1] * 2 / 6);
        return bs;
    }


    private double[][] getSplineMatrix(double[] hs, double[] y) {
        double[][] result = new double[y.length][y.length + 1];

        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y.length; j++)
                result[i][j] = 0;
        }
        for (int i = 1; i < y.length - 1; i++) {
            result[i][i - 1] = hs[i - 1];
            result[i][i] = 2 * (hs[i] + hs[i - 1]);
            result[i][i + 1] = hs[i];
            result[i][hs.length + 1] = 3 * ((y[i + 1] - y[i]) / hs[i] - (y[i] - y[i - 1]) / hs[i - 1]);
        }
        result[0][0] = 1;
        result[hs.length][hs.length] = 1;
        result[0][hs.length + 1] = 0;
        result[hs.length][hs.length + 1] = 0;
        return result;
    }

    private double[] getDArray(double[] hs, double[] cs) {
        double[] ds = new double[y.length];
        ds[0] = cs[0]/hs[0];
        for (int i = 1; i < ds.length; i++)
            ds[i] = (cs[i] - cs[i - 1]) / (3 * hs[i - 1]);
        return ds;
    }
}
