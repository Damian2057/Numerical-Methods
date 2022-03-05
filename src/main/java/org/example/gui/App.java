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
        try {
            if(stop >=1) {
                int stop2 = (int) stop;
                System.out.println(bisection.bisectionAlgorithm(lowerLimit,upperLimit,stop2));
                System.out.println(falsi.falsiAlgorithm(lowerLimit,upperLimit,stop2));
            } else {
                System.out.println(bisection.bisectionAlgorithm(lowerLimit, upperLimit, stop));
                System.out.println(falsi.falsiAlgorithm(lowerLimit, upperLimit, stop));
            }
        } catch (NoZeroException e) {
            System.out.println("Funkcja wydaje sie nie miec miejsca zerowego na podanym przedziale");
        } catch (Exception e) {
            System.out.println("Wykryto blad");
        }

        double index = lowerLimit;
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        while(index <= upperLimit) {
            x.add(index);
            y.add(bisection.f(index));
            index+=Math.abs((upperLimit-lowerLimit)*(1.0/100));
        }

        final XYSeriesDemo demo = new XYSeriesDemo(x,y);
        demo.pack();
        demo.setVisible(true);

        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}