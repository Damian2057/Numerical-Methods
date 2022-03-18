package org.example.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.example.exception.NoZeroException;
import org.example.model.*;

public class App {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Program rozwiazujacy uklad rownan metoda iteracji prostej Jacobiego\n");
        double stop;
        System.out.println("Podaj warunek stopu (liczba >=1 to liczba iteracji, <1 to dokladnosc): ");
        stop = Double.parseDouble(scanner.nextLine());

        FileReader reader = new FileReader();
        int[][] matrix;
        try {
            matrix = reader.getSystemOfEquations("@../../equations/equation.txt");
            System.out.println(matrix[0][1]);
        } catch (Exception e) {
            System.out.println("there was an error loading the equation");
        }


        try {
            if(stop >=1) {
                int stop2 = (int) stop;

            } else {

            }
        } catch (NoZeroException e) {
            System.out.println("Blad macierzy()");
        } catch (Exception e) {
            System.out.println("Wykryto blad");
        }


    }
}


