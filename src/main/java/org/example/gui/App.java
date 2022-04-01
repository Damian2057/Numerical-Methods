package org.example.gui;

import java.util.Scanner;

import org.example.exception.DataException;
import org.example.model.*;

public class App {

    public static void main(String[] args) {

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




    }
}


