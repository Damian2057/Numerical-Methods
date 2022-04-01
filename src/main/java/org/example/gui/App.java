package org.example.gui;

import java.util.Scanner;

import org.example.exception.DataException;
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
        minRange = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the number of interpolation nodes :");
        numberOfNodes = Integer.parseInt(scanner.nextLine());


    }
}


