package com.alicher.util;

import com.alicher.maths.Spline;
import com.alicher.models.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class GraphDisplay {
    Spline spline;
    String function;
    double[] manual;

    public GraphDisplay(Spline spline, String function, double[] manual) {
        this.function = function;
        this.spline = spline;
        this.manual = manual;
    }


    public void draw(Function function) {
        double[] xData = new double[10000];
        double[] yDataSpline = new double[10000];
        double[] yData = new double[10000];

        XYSeries xySeries1 = new XYSeries("xySeries1");
        XYSeries xySeries2 = new XYSeries("xySeries2");

        double step = (spline.getX()[spline.getX().length - 1] - spline.getX()[0]) / 1000;

        double x = spline.getX()[0] - 0.1;

        xData[0] = x;
        yDataSpline[0] = spline.getFuncValue(x, 0);
        if (function != null)
            yData[0] = function.valueAt(x);
//        else yData[0] = manual[0];
        double curX = spline.getX()[0];
        int k = 0;

        int i = 0;
        while (x < spline.getX()[spline.getX().length - 1]) {
            i++;
            x += step;
            if (x > curX && k < spline.getX().length - 1) {
                k++;
                curX = spline.getX()[k];
            }
            xData[i] = x;
            yDataSpline[i] = spline.getFuncValue(x, k);
            if (function != null)
                yData[i] = function.valueAt(x);
//            else yData[i] = manual[i];
            xySeries1.add(xData[i], yDataSpline[i]);
//            xySeries2.add(xData[i], yData[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries1);
        dataset.addSeries(xySeries2);
        JFreeChart chart = ChartFactory.createXYLineChart("F(x)", "x", "y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = chart.getXYPlot();
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        XYSplineRenderer renderer = new XYSplineRenderer(10000);
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1, -1, 2, 2));
        plot.setDataset(0, dataset);
        for (int j = 0; j < spline.getX().length; j++) {
            XYSeries xySeries3 = new XYSeries("xySeries3");
            XYSeriesCollection pointDataset = new XYSeriesCollection();
            pointDataset.addSeries(xySeries3);
            xySeries3.add(spline.getX()[j], spline.getA()[j]);
            plot.setDataset(j + 1, pointDataset);
            plot.setRenderer(j + 1, renderer);
        }
        ChartFrame frame = new ChartFrame("", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
