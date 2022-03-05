package org.example.model;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;

public class XYSeriesDemo extends ApplicationFrame {

    public XYSeriesDemo(ArrayList<Double> x, ArrayList<Double> y) {

        super("wykres");

        final XYSeries series = new XYSeries("Random Data");

        for(int i=0; i<x.size(); i++) {
            series.add(x.get(i),y.get(i));
        }

        final XYSeries osX = new XYSeries("Os X");
        osX.add(x.get(0).doubleValue(), 1.0);
        osX.add(x.get(x.size()-1).doubleValue(),1);

        final Marker start = new ValueMarker(0.0);
        start.setPaint(Color.green);



        final XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(osX);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Wykres funkcji",
                "os X",
                "os Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }}