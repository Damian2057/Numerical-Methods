package org.example.model;

import java.util.Scanner;

public class NewtonCotes {
    private final FunctionContainer container;
    private final double epsilon;
    private StringBuilder builder = new StringBuilder();
    private double lowerLimit;
    private double upperLimit;
    private int choice;

    private void info() {
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
                lowerLimit=0;
                upperLimit=10;
                int n=6;
                double suma = Newton_Cotes(lowerLimit,upperLimit,n);
                lowerLimit = upperLimit;
                double delta = 0.1;
                double z;
                do
                {
                    upperLimit = lowerLimit + delta;
                    z = Newton_Cotes(lowerLimit,upperLimit,n);
                    suma += z;
                    lowerLimit += delta;
                    System.out.println(z);
                }while (epsilon < Math.abs(z));
                System.out.println("granica policzona");
                System.out.println(suma);
                System.out.println(n);
            }
            case 2 -> {

            }
            default -> {
                System.out.println("Invalid option");
                return;
            }
        }
    }

    private double Newton_Cotes(double lowerLimit, double upperLimit, int count) {
        if(choice == 1) {
            return (lowerLimit - upperLimit) / (6 * count)
                    * (expFunction(upperLimit) + expFunction(lowerLimit)
                    +(2 * firstSum(count)) + (4 * secondSum(count)));
        } else {
            return (lowerLimit - upperLimit) / (6 * count)
                    * (container.function(upperLimit) + container.function(lowerLimit)
                    + (2 * firstSum(count)) + (4 * secondSum(count)));
        }
    }

    private double expFunction(double x) {
        return container.function(x) * Math.exp(x);
    }

    private double firstSum(int count){
        double finalSum = 0;
        double middle = Math.abs(upperLimit-lowerLimit)/count;
        for (int i = 1; i < count; i++) {
            if (choice == 1) {
                finalSum += expFunction(upperLimit + (middle * i));
            } else {
                finalSum += container.function(upperLimit + (middle * i));
            }
        }
        return finalSum;
    }

    private double secondSum(int count){
        double finalSum = 0;
        double middle = Math.abs(upperLimit-lowerLimit) / count;
        double poczatekT= upperLimit - (middle / 2);
        for (int i = 1; i <= count; i++) {
            if (choice == 1) {
                finalSum += expFunction(poczatekT + (middle * i));
            } else {
                finalSum += container.function(poczatekT + (middle * i));
            }
        }
        return finalSum;
    }
}
