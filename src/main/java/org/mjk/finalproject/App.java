package org.mjk.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMinWidth(800); // Minimum width
        stage.setMinHeight(450); // Minimum height
        stage.setMaxWidth(1080); // Maximum width
        stage.setMaxHeight(600); // Maximum height
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}