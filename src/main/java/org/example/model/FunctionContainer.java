package org.example.model;

import org.example.exception.DataException;
import java.util.Scanner;

public class FunctionContainer {

    private final int choice;
    private final StringBuilder stringBuilder = new StringBuilder();

    public FunctionContainer() {
        initFunctions();
        Scanner scanner= new Scanner(System.in);
        System.out.println("Select functions for the method:");
        System.out.println(stringBuilder);

        choice = Integer.parseInt(scanner.nextLine());
    }

    private void initFunctions() {
        stringBuilder.append("Available functions:\n")
                .append("[1]: |x|\n")
                .append("[2]: x*x+x-1\n")
                .append("[3]: |2*cos(x)|+2\n")
                .append("[4]: sin(x)\n")
                .append("[5]: sin(x)*x+5\n")
                .append("[6]: x*x*x+x-1\n")
                .append("[7] 5x-2\n")
                .append("[8] cos(x)\n")
                .append("[9] x");
    }

    public double function(double x) {
        switch (choice) {
            case 1 -> {
                return Math.abs(x);
            }
            case 2 -> {
                return x*x+x-1;
            }
            case 3 -> {
                return Math.abs(2*Math.cos(x)) + 2;
            }
            case 4 -> {
                return Math.sin(x);
            }
            case 5 -> {
                return Math.sin(x)*x+5;
            }
            case 6 -> {
                return x*x*x+x-1;
            }
            case 7 -> {
                return 5*x-2;
            }
            case 8 -> {
                return Math.cos(x);
            }
            case 9 -> {
                return x;
            }
        }
        throw new DataException("Bad function ID");
    }
}
