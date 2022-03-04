module Zadanie1 {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    opens org.example.model;
    exports org.example.model;

    opens org.example.gui;
    exports org.example.gui;
}