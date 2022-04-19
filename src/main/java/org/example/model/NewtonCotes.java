package org.example.model;

import java.util.Scanner;

public class NewtonCotes {
    private final FunctionContainer container;
    private final double epsilon;
    private double lowerLimit;
    private double upperLimit;
    private int choice;

    private void getInfo() {
        Scanner scanner= new Scanner(System.in);
        String builder = """
                Choose a range:
                [1] -> [0,+inf)
                [2] Enter it yourself""";
        System.out.println(builder);
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

    private double expFunction(double x) {
        return container.function(x) * Math.exp(-x);
    }

    public void calculate() {
        getInfo();
        double sum = 0;
        int interval;
        switch (choice) {
            case 1 -> {
                lowerLimit = 0;
                upperLimit = 10;
                sum = newtonCotesQuadrature(lowerLimit,upperLimit,1);
                double upperSum = newtonCotesQuadrature(lowerLimit,upperLimit,2);
                interval = 3; //number of compartments
                while (Math.abs(sum-upperSum) > epsilon) {
                    sum = upperSum;
                    upperSum = newtonCotesQuadrature(lowerLimit,upperLimit,interval);
                    interval++;
                }
                double factor = 0.1;
                while (epsilon
                        > newtonCotesQuadrature(lowerLimit+factor,upperLimit, interval)) {
                    sum += newtonCotesQuadrature(lowerLimit+factor,upperLimit,interval);
                    lowerLimit += factor;
                }
            } case 2 -> {
                interval = 1;
                double currentResult = 0;
                double previousResult;
                do {
                    interval++;
                    previousResult = currentResult;
                    currentResult = newtonCotesQuadrature(lowerLimit, upperLimit, interval);
                } while (epsilon < Math.abs(currentResult - previousResult));
                System.out.println("Result of the calculated area under the integral plot: " + sum);
                System.out.println("For interval: " + interval);
            } default -> {
                System.out.println("Invalid option");
                return;
            }
        }
        System.out.println("Result: "+ sum);
        System.out.println("For interval: " + interval);
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
