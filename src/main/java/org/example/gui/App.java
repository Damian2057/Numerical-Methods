package org.example.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.example.model.*;

public class App {

    private static void getInfo() {
        String stringBuilder = """
                Approximation methods:
                """;
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner= new Scanner(System.in);
            getInfo();

            //choice function
            FunctionContainer functionContainer;
            while (true) {
                try {
                    functionContainer = new FunctionContainer();
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            double lowerLimit = 0;
            double upperLimit = 0;
            System.out.println("Enter the lower approximation range:");
            lowerLimit = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter the upper approximation range:");
            upperLimit = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter the number of nodes:");
            int nodesCount = scanner.nextInt();

            System.out.println("Choose the approximation criterion:\n" +
                    "1. Accuracy Criterion\n" +
                    "2. The criterion of the degree of the approximating polynomial");
            int choice = scanner.nextInt();

            double[] polymial = null;
            Approximation approximation = new Approximation(functionContainer,nodesCount);

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.println("Enter the accuracy of approximation");
                        double epsilon = 0.0;
                        epsilon = scanner.nextDouble();
                        int approxLvl = 1;
                        do {
                            polymial = approximation.polynomialListCoefficients(approxLvl);
                            approxLvl++;
                        } while (approximation.gaussError(polymial) > epsilon);
                        System.out.println(Arrays.toString(polymial));
                        System.out.println(approximation.gaussError(polymial));
                    } catch (Exception e) {
                        return;
                    }
                }
                case 2 -> {
                    System.out.println("Enter the degree of the polynomial: ");
                    int n = scanner.nextInt();
                    polymial = approximation.polynomialListCoefficients(n);
                    System.out.println(Arrays.toString(polymial));
                    approximation.gaussError(polymial);
                }
                default -> {
                    System.out.println("error");
                }
                }
            ArrayList<Double> originXFunction = new ArrayList<>();
            ArrayList<Double> originYFunction = new ArrayList<>();
            ArrayList<Double> approxXFunction = new ArrayList<>();
            ArrayList<Double> approxYFunction = new ArrayList<>();

            double accuracyLeap = 0.01;
            double temp = lowerLimit; //started X on the chart

            while (temp <= upperLimit) {
                originXFunction.add(temp);
                originYFunction.add(functionContainer.function(temp));

                approxXFunction.add(temp);
                approxYFunction.add(approximation.valueTest(polymial,temp));

                temp += accuracyLeap;
            }

            XYSeriesDemo xySeriesDemo = new XYSeriesDemo(originXFunction,originYFunction, approxXFunction
                    ,approxYFunction);
            xySeriesDemo.pack();
            xySeriesDemo.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}


