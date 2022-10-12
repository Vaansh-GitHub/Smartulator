package com.calculator.calculator_dev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Calculator_Design.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Smartulator!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("logo.png")));
    }

    public static void main(String[] args) {
        launch();
    }
}