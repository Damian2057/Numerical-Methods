package org.example.gui;

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

            double[] polymial;
            Approximation approximation = new Approximation(functionContainer,nodesCount);

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the accuracy of approximation");
                    double epsilon = Double.parseDouble(scanner.nextLine());
                    int approxLvl = 1;
                    do {
                        polymial = approximation.polynomialListCoefficients(approxLvl);
                        approxLvl++;
                    } while (approximation.gaussError() > epsilon);
                }
                case 2 -> {
                    System.out.println("Enter the degree of the polynomial: ");
                    int n = scanner.nextInt();
                    polymial = approximation.polynomialListCoefficients(n);
                }
                default -> {
                    System.out.println("error");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


