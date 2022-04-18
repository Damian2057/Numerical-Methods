package org.example.model;

import java.util.Scanner;

public class NewtonCotes {
    private final FunctionContainer container;
    private final double epsilon;
    private double lowerLimit;
    private double upperLimit;
    private int choice;

    private void info() {
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
        info();
        switch (choice) {
            case 1 -> {
                lowerLimit = 0;
                upperLimit = 10;
                int interval = 6; //number of compartments
                double result = Newton_Cotes(lowerLimit,upperLimit,interval);
                lowerLimit = upperLimit;
                double factor = 0.1;
                double temp = 0;
                do {
                    upperLimit = lowerLimit + factor;
                    temp = Newton_Cotes(lowerLimit,upperLimit,interval);
                    result += temp;
                    lowerLimit += factor;
                } while (epsilon < Math.abs(temp));
                System.out.println("Result: "+ result);
                System.out.println("For nodes: " + interval);
            }
            case 2 -> {
                int interval = 1;
                double currentResult = 0;
                double previousResult;
                do {
                    interval++;
                    previousResult = currentResult;
                    currentResult = Newton_Cotes(lowerLimit, upperLimit, interval);
                } while (epsilon > Math.abs(currentResult - previousResult));
                System.out.println("Result:" + currentResult);
            }
            default -> {
                System.out.println("Invalid option");
                return;
            }
        }
    }

    private double Newton_Cotes(double lowerLimit, double upperLimit, int count) {
        if(choice == 1) {
            return (upperLimit - lowerLimit) / (6 * count) * (expFunction(lowerLimit)
                    + expFunction(upperLimit) + (2 * firstSum(count)) + (4 * secondSum(count)));
        } else {
            return (lowerLimit - upperLimit) / (6 * count)
                    * (container.function(upperLimit) + container.function(lowerLimit)
                    + (2 * firstSum(count)) + (4 * secondSum(count)));
        }
    }

    private double expFunction(double x) {
        return container.function(x) * Math.exp(-x);
    }

    private double firstSum(int count){
        double finalSum = 0;
        double middle = Math.abs(lowerLimit - upperLimit) / count;
        for (int i = 1; i < count; i++) {
            if (choice == 1) {
                finalSum += expFunction(lowerLimit + (middle * i));
            } else {
                finalSum += container.function(lowerLimit + (middle * i));
            }
        }
        return finalSum;
    }

    private double secondSum(int count){
        double finalSum = 0;
        double middle = Math.abs(lowerLimit - upperLimit) / count;
        double middleDif = lowerLimit - (middle / 2);
        for (int i = 1; i <= count; i++) {
            if (choice == 1) {
                finalSum += expFunction(middleDif + (middle * i));
            } else {
                finalSum += container.function(middleDif + (middle * i));
            }
        }
        return finalSum;
    }
}
