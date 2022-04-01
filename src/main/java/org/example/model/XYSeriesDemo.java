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

    public XYSeriesDemo(int number,ArrayList<Double> originX, ArrayList<Double> originY,
                        ArrayList<Double> interpolatedXFunction, ArrayList<Double> interpolateYFunction) {

        super("Ilosc wezlow"+ number);

        final XYSeries series = new XYSeries("f(x)");

        int i=0;
        for(i=0; i<originX.size(); i++) {
            series.add(originX.get(i),originY.get(i));
        }
        final XYSeriesCollection data = new XYSeriesCollection(series);



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
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, false);
        plot.setRenderer(renderer);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 540));
        setContentPane(chartPanel);

    }}