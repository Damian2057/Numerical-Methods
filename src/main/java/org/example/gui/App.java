package org.example.gui;

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
                    Simpson simpson = new Simpson();
                }
                case 2 -> {
                    Hermite hermite = new Hermite();
                }
                default -> {
                    System.out.println("incorrect selection");
                    return;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


