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
import java.util.Collections;

public class XYSeriesDemo extends ApplicationFrame {

    public XYSeriesDemo(String title,ArrayList<Double> x, ArrayList<Double> y, double zero) {

        super("wykres"+ title);

        final XYSeries series = new XYSeries("f(x)");

        int i=0;
        for(i=0; i<x.size(); i++) {
            series.add(x.get(i),y.get(i));
        }

        final XYSeries osX = new XYSeries("Oś X");
        osX.add(x.get(0).doubleValue(), (y.get(i-1)+x.get(0))/2);
        osX.add(x.get(x.size()-1).doubleValue(),(x.get(i-1)+x.get(0))/2);

        ArrayList<Double> copy = (ArrayList<Double>) new ArrayList<>(y).clone();

        Collections.sort(copy);
        final XYSeries osY = new XYSeries("Oś Y");
        osY.add((x.get(i-1)+x.get(0))/2,copy.get(0));
        osY.add((x.get(i-1)+x.get(0))/2,copy.get(copy.size()-1));


        final XYSeries miejsceZerowe = new XYSeries("Miejsce zerowe");
        miejsceZerowe.add(zero,0);

        final XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(osX);
        data.addSeries(osY);
        data.addSeries(miejsceZerowe);



        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Wykres funkcji z algorytmem "+title,
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