package org.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.model.example;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        example example = new example();
        System.out.println("test");
    }

    public static void main(String[] args) {
        launch();
    }
}
