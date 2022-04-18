package org.example.gui;

import java.util.ArrayList;
import java.util.Scanner;
import org.example.model.*;

public class App {

    private static void getInfo() {
        String stringBuilder = """
                Numerical integration with two different ways:
                Select Method:
                [1]: complex Newton-Cotes quadrature based on three nodes
                [2]: integration on the interval (Laguerre polynomials)
                """;
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner= new Scanner(System.in);
            //choice method
            getInfo();
            int choice = Integer.parseInt(scanner.nextLine());

            //choice function
            FunctionContainer functionContainer = null;
            while (true) {
                try {
                    functionContainer = new FunctionContainer();
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            //calculation
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the accuracy of the result:");
                    double epsilon = Double.parseDouble(scanner.nextLine());
                    NewtonCotes newtonCotes = new NewtonCotes(functionContainer, epsilon);
                    newtonCotes.calculate();

                }
                case 2 -> {
                    Laguerrea laguerrea = new Laguerrea(functionContainer);
                    ArrayList<Double> result = laguerrea.calculateForEveryNode();
                    for (int i = 0; i < result.size(); i++) {
                        System.out.println("Node: "+ (i+2) + ",Result:" + result.get(i));
                    }
                }
                default -> {
                    System.out.println("incorrect selection");
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}


