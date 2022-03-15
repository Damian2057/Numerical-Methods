package org.example.model.functiontypes;

import java.util.Scanner;

public class Exponential {
    private double base;
    private double freeFactor;

    public Exponential() {
        boolean flag = true;
        Scanner scanner= new Scanner(System.in);
        while (flag) {
            System.out.println("Podaj podstawe funkcji wykladniczej");
            this.base = Double.parseDouble(scanner.nextLine());
            if(base > 0) {
                flag = false;
            }
        }
        System.out.println("Podaj wyraz wolny");
        this.freeFactor = Double.parseDouble(scanner.nextLine());
    }

    public double getBase() {
        return base;
    }

    public double getFreeFactor() {
        return freeFactor;
    }
}
