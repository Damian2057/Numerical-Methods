package org.example.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Scanner;
import org.example.exception.NoZeroException;
import org.example.model.Bisection;
import org.example.model.Falsi;
import org.example.model.XYSeriesDemo;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {



        System.out.println("Podaj wzor funkcji np. sin(x+1): ");
        Scanner scanner= new Scanner(System.in);
        String wzor = scanner.nextLine();

        //podajemy dolny i gorny przedzial oraz liczbe iteracji/dokladnosc
        double lowerLimit , upperLimit, stop;
        System.out.print("Podaj dolny przedzial: ");
        lowerLimit = scanner.nextDouble();
        System.out.print("Podaj gorny przedzial: ");
        upperLimit = scanner.nextDouble();
        System.out.println("Podaj warunek stopu (liczba >=1 to liczba iteracji, <1 to dokladnosc): ");
        stop = scanner.nextDouble();

        Bisection bisection = new Bisection(wzor);
        Falsi falsi = new Falsi(wzor);
        double bisectionZero = 0;
        double falsiZero = 0;
        try {
            if(stop >=1) {
                int stop2 = (int) stop;
                bisectionZero = bisection.bisectionAlgorithm(lowerLimit,upperLimit,stop2);
                System.out.println("B "+ bisectionZero);
                falsiZero = falsi.falsiAlgorithm(lowerLimit,upperLimit,stop2);
                System.out.println("F "+ falsiZero);
            } else {
                bisectionZero = bisection.bisectionAlgorithmE(lowerLimit, upperLimit, stop);
                System.out.println("B "+ bisectionZero);
                falsiZero = falsi.falsiAlgorithmE(lowerLimit, upperLimit, stop);
                System.out.println("F "+ falsiZero);
            }
        } catch (NoZeroException e) {
            System.out.println("Funkcja wydaje sie nie miec miejsca zerowego na podanym przedziale");
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

        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}