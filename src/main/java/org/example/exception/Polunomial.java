package org.example.exception;

import java.util.ArrayList;
import java.util.Scanner;

public class Polunomial extends Action{
    private ArrayList<Double> numbers = new ArrayList<>();
    private int n;

    public Polunomial() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Podaj stopien wielomianu: ");
        this.n = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj kolejne wspolczynniki przy x");
        for (int i = 0; i < n; i++) {
            System.out.println("Przy "+ (n-i)+" potedze.");
            numbers.add(Double.parseDouble(scanner.nextLine()));
        }
        System.out.println("Podaj wyraz wolny");
        numbers.add(Double.parseDouble(scanner.nextLine()));
    }
}
