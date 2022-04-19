package org.example.model;

import java.util.Scanner;

public class NewtonCotes {
    private final FunctionContainer container;
    private final double epsilon;
    private double lowerLimit;
    private double upperLimit;
    private int choice;

    private void getInfo() {
        StringBuilder builder = new StringBuilder();
        Scanner scanner= new Scanner(System.in);
        builder.append("Choose a range:\n")
                .append("[1] -> [0,+inf)\n")
                .append("[2] Enter it yourself");
        System.out.println(builder.toString());
        choice = scanner.nextInt();
        if(choice == 2) {
            System.out.println("Enter the lower range:");
            lowerLimit = scanner.nextDouble();
            System.out.println("Enter the upper range:");
            upperLimit = scanner.nextDouble();
        }
    }

    public NewtonCotes(FunctionContainer container, double epsilon) {
        this.container = container;
        this.epsilon = epsilon;
    }

    public void calculate() {
        getInfo();
        switch (choice) {
            case 1 -> {
                lowerLimit = 0;
                upperLimit = 15;
                int interval = 10; //number of compartments
                double result = newtonCotesQuadrature(lowerLimit,upperLimit,interval);
                lowerLimit = upperLimit;
                double factor = 0.01;
                double temp = 0;
                do {
                    upperLimit = lowerLimit + factor;
                    temp = newtonCotesQuadrature(lowerLimit,upperLimit,interval);
                    result += temp;
                    lowerLimit += factor;
                } while (epsilon < Math.abs(temp));
                System.out.println("Result: "+ result);
                System.out.println("For interval: " + interval);
            }
            case 2 -> {
                int interval = 1;
                double currentResult = 0;
                double previousResult;
                do {
                    interval++;
                    previousResult = currentResult;
                    currentResult = newtonCotesQuadrature(lowerLimit, upperLimit, interval);
                } while (epsilon > Math.abs(currentResult - previousResult));
                System.out.println("Result: " + currentResult);
                System.out.println("For interval: " + interval);
            }
            default -> {
                System.out.println("Invalid option");
                return;
            }
        }
    }

    private double newtonCotesQuadrature(double lowerLimit, double upperLimit, int countOfIntervals) {
        if(choice == 1) {
            return (upperLimit - lowerLimit) / (6 * countOfIntervals) * (expFunction(lowerLimit)
                    + expFunction(upperLimit) + (2 * firstSummary(countOfIntervals)) + (4 * secondSummary(countOfIntervals)));
        } else {
            return (upperLimit - lowerLimit) / (6 * countOfIntervals)
                    * (container.function(lowerLimit) + container.function(upperLimit)
                    + (2 * firstSummary(countOfIntervals)) + (4 * secondSummary(countOfIntervals)));
        }
    }

    private double expFunction(double x) {
        return container.function(x) * Math.exp(-x);
    }

    private double firstSummary(int countOfIntervals){
        double finalSum = 0;
        double middle = Math.abs(lowerLimit - upperLimit) / countOfIntervals;
        for (int i = 1; i < countOfIntervals; i++) {
            if (choice == 1) {
                finalSum += expFunction(lowerLimit + (middle * i));
            } else {
                finalSum += container.function(lowerLimit + (middle * i));
            }
        }
        return finalSum;
    }

    private double secondSummary(int countOfIntervals){
        double finalSum = 0;
        double middle = Math.abs(lowerLimit - upperLimit) / countOfIntervals;
        double middleDif = lowerLimit - (middle / 2);
        for (int i = 1; i <= countOfIntervals; i++) {
            if (choice == 1) {
                finalSum += expFunction(middleDif + (middle * i));
            } else {
                finalSum += container.function(middleDif + (middle * i));
            }
        }
        return finalSum;
    }
}
