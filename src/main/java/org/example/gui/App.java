package org.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.model.Bisection;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Bisection bisection = new Bisection("xd");
        System.out.println(bisection.bisectionAlgorithm(0,2,0.0001));
        System.out.println("test");
    }

    public static void main(String[] args) {
        launch();
    }
}
