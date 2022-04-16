package org.example.model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;
import java.util.ArrayList;

public class XYSeriesDemo extends ApplicationFrame {

    public XYSeriesDemo(int number,ArrayList<Double> originX, ArrayList<Double> originY,
                        ArrayList<Double> interpolatedXFunction, ArrayList<Double> interpolatedYFunction
            ,ArrayList<Node> nodes) {

        super("Ilosc wezlow"+ number);

        final XYSeries originSeries = new XYSeries("f(x)");

        for(int i=0; i<originX.size(); i++) {
            originSeries.add(originX.get(i),originY.get(i));
        }

        final XYSeries interpolatedSeries = new XYSeries("f'(x)");

       final XYSeries point = new XYSeries("nodes");

        for (int i = 0; i < nodes.size(); i++) {
            point.add(nodes.get(i).getX(),nodes.get(i).getY());
        }

        for (int i = 0; i < interpolatedXFunction.size(); i++) {
            interpolatedSeries.add(interpolatedXFunction.get(i),interpolatedYFunction.get(i));
        }


        final XYSeriesCollection data = new XYSeriesCollection(originSeries);
        data.addSeries(interpolatedSeries);
        data.addSeries(point);


        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Ilosc wezlow "+ number,
                "oś X",
                "oś Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);

        renderer.setSeriesShape(2,new Rectangle(-2,-2,4,4));
        renderer.setSeriesShapesVisible(2,true);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesFilled(2,true);
        plot.setRenderer(renderer);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 540));
        setContentPane(chartPanel);

    }
}