package org.example.model.functiontypes;


import java.util.Scanner;

public class Trigonometry {

    private String funkcja;

    public Trigonometry() {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Podaj funkcje trygonometryczna (sin/cos/tan/ctg): ");
        this.funkcja = scanner.nextLine();
    }

    public String getFunkcja() {
        return funkcja;
    }
}
