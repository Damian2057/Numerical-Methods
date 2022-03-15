package org.example.gui;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.exception.NoZeroException;
import org.example.model.*;
import org.example.model.functiontypes.Exponential;
import org.example.model.functiontypes.Polunomial;
import org.example.model.functiontypes.Trigonometry;

public class App {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        int n = 1;
        boolean flag = false;
        while(flag == false) {
            System.out.println("Podaj liczbe zlozen:");
            n = Integer.parseInt(scanner.nextLine());
            if(n == 1 || n==2 || n==3) flag = true;
        }




        String wybor1 = "";
        StringBuilder type = new StringBuilder();
        for (int i = 0; i < n; i++) {
            System.out.println("Podaj "+(i+1)+" typ funkcji (tryg/wiel/wyk): ");
            wybor1 = scanner.nextLine();
            switch (wybor1) {
                case "tryg"-> {
                    type.append("T");
                }
                case "wyk"-> {
                    type.append("Z");
                }
                case "wiel"-> {
                    type.append("W");
                }
                default-> {
                    System.out.println("Blad wprowadzania danych!");
                    i++;
                }
            }
        }

        functionInterpreter functionInterpreter = new functionInterpreter(type.toString());

        //podajemy dolny i gorny przedzial oraz liczbe iteracji/dokladnosc
        double lowerLimit , upperLimit, stop;
        System.out.print("Podaj dolny przedzial: ");
        lowerLimit = Double.parseDouble(scanner.nextLine());;
        System.out.print("Podaj gorny przedzial: ");
        upperLimit = Double.parseDouble(scanner.nextLine());
        System.out.println("Podaj warunek stopu (liczba >=1 to liczba iteracji, <1 to dokladnosc): ");
        stop = Double.parseDouble(scanner.nextLine());

        Bisection bisection = new Bisection(functionInterpreter);
        Falsi falsi = new Falsi(functionInterpreter);
        double bisectionZero = 0;
        double falsiZero = 0;
        try {
            if(stop >=1) {
                int stop2 = (int) stop;
                bisectionZero = bisection.bisectionAlgorithm(lowerLimit,upperLimit,stop2);
                falsiZero = falsi.falsiAlgorithm(lowerLimit,upperLimit,stop2);
                System.out.println("B "+ bisectionZero);
                System.out.println("F "+ falsiZero);
            } else {
                bisectionZero = bisection.bisectionAlgorithmE(lowerLimit, upperLimit, stop);
                falsiZero = falsi.falsiAlgorithmE(lowerLimit, upperLimit, stop);
                System.out.println("B "+ bisectionZero);
                System.out.println("F "+ falsiZero);
            }
        } catch (NoZeroException e) {
            System.out.println("Funkcja na podanym przedziale wydaje sie nie spelniac warunku roznego znaku na krancach przedzialu");
        } catch (Exception e) {
            System.out.println("Wykryto blad");
        }

        double index = 0;
        double value = 0;

        if(lowerLimit < upperLimit) {
            index = lowerLimit;
            value = upperLimit;
        } else {
            index = upperLimit;
            value = lowerLimit;
        }

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        while(index <= value) {
            x.add(index);
            y.add(bisection.f(index));
            index+=Math.abs((upperLimit-lowerLimit)*(1.0/300));
        }


        //Bisection graph
        final XYSeriesDemo demoBisection = new XYSeriesDemo("Bisekcja",x,y, bisectionZero);
        demoBisection.pack();
        demoBisection.setVisible(true);

        //Falsi graph
        final XYSeriesDemo demoFalsi = new XYSeriesDemo("Falsi",x,y, falsiZero);
        demoFalsi.pack();
        demoFalsi.setVisible(true);
    }
}


