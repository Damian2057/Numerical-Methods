package org.example.gui;

import java.util.ArrayList;
import java.util.Scanner;
import org.example.model.*;

public class App {

    public static void main(String[] args) {

        double minRange = 0;
        double maxRange = 0;
        int numberOfNodes = 0;
        Scanner scanner= new Scanner(System.in);

        System.out.println("Lagrange interpolation methods on Chebyshev nodes\n");

        FunctionContainer functionContainer = null;
        while (true) {
            try {
                functionContainer = new FunctionContainer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter the lower range:");
        minRange = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the upper range:");
        maxRange = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the number of interpolation nodes :");
        numberOfNodes = Integer.parseInt(scanner.nextLine());

        ChebyshevInterpolation chebyshevInterpolation
                = new ChebyshevInterpolation(functionContainer,minRange,maxRange,numberOfNodes);

        ArrayList<Double> originXFunction = new ArrayList<>();
        ArrayList<Double> originYFunction = new ArrayList<>();
        ArrayList<Double> interpolatedXFunction = new ArrayList<>();
        ArrayList<Double> interpolateYFunction = new ArrayList<>();

        double accuracyLeap = 0.01;
        double temp = minRange; //started X on the chart

        while (temp <= maxRange) {
            originXFunction.add(temp);
            originYFunction.add(functionContainer.function(temp));

            interpolatedXFunction.add(temp);
            interpolateYFunction.add(chebyshevInterpolation.getInterpolatedValue(temp));

            temp += accuracyLeap;
        }

        //To debug

        XYSeriesDemo xySeriesDemo = new XYSeriesDemo(numberOfNodes
                ,originXFunction,originYFunction, interpolatedXFunction
                ,interpolateYFunction,chebyshevInterpolation.getNodes());
        xySeriesDemo.pack();
        xySeriesDemo.setVisible(true);

    }
}


