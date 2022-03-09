package org.example.model.functiontypes;

import java.util.Scanner;

public class Exponential {
    private double base;
    private double freeFactor;

    public Exponential() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Podaj podstawe funkcji wykladniczej");
        this.base = Double.parseDouble(scanner.nextLine());
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
