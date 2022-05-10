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
import java.util.ArrayList;

public class XYSeriesDemo extends ApplicationFrame {

    public XYSeriesDemo(ArrayList<Double> originX, ArrayList<Double> originY,
                        ArrayList<Double> approxX, ArrayList<Double> approxY) {

        super("Ilość węzłów: ");

        final XYSeries originSeries = new XYSeries("f(x)");

        for(int i=0; i<originX.size(); i++) {
            originSeries.add(originX.get(i),originY.get(i));
        }

        final XYSeries approxSeries = new XYSeries("f'(x)");

        for (int i = 0; i < approxX.size(); i++) {
            approxSeries.add(approxX.get(i),approxY.get(i));
        }


        final XYSeriesCollection data = new XYSeriesCollection(originSeries);
        data.addSeries(approxSeries);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Aproksymacja: ",
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
        plot.setRenderer(renderer);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 540));
        setContentPane(chartPanel);

    }
}