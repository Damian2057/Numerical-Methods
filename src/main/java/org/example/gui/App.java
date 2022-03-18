package org.example.gui;

import java.util.Scanner;
import org.example.exception.IndefiniteLayoutException;
import org.example.exception.NoSolutionException;
import org.example.model.*;

public class App {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Program rozwiazujacy uklad rownan metoda iteracji prostej Jacobiego\n");
        double stop;
        System.out.println("Podaj warunek stopu (liczba >=1 to liczba iteracji, <1 to dokladnosc): ");
        stop = Double.parseDouble(scanner.nextLine());

        FileReader reader = new FileReader();
        int[][] matrix = null;
        int numberOfEquations = 0;

        try {
            matrix = reader.getSystemOfEquations("@../../equations/equation.txt");
            numberOfEquations = reader.getNumberOfEquations();
        } catch (Exception e) {
           e.printStackTrace();
        }

        JacobMethod jacobMethod = new JacobMethod(numberOfEquations,matrix);
        int[] result = new int[numberOfEquations];

        try {
            if(stop >=1) {
                int parseStop = (int) stop;
                result = jacobMethod.iterationSolver(parseStop);
            } else {
                result = jacobMethod.accuracySolver(stop);
            }
        } catch (NoSolutionException | IndefiniteLayoutException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Error occurred");
            return;
        }


        System.out.println("The solution to the equation is");
        for (int i = 0; i < numberOfEquations; i++) {
          //  System.out.println("x" + (i+1) + " = " +result[i]);
        }


    }
}


