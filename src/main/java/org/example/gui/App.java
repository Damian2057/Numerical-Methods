package org.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;
import org.example.model.Bisection;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Bisection bisection = new Bisection("xd");

        double lowerLimit , upperLimit, dokladnosc;

        Scanner sc= new Scanner(System.in);
        System.out.print("Podaj dolny przedzial: ");
        lowerLimit = sc.nextDouble();
        System.out.print("Podaj gorny przedzial: ");
        upperLimit = sc.nextDouble();
        System.out.print("Podaj dokladnosc: ");
        dokladnosc = sc.nextDouble();

        System.out.println(bisection.bisectionAlgorithm(lowerLimit,upperLimit,dokladnosc));
    }

    public static void main(String[] args) {
        launch();



    }
}
