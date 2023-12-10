package org.mjk.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Image appIcon = new Image(getClass().getResourceAsStream("/org/mjk/finalproject/mugPNG.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Tea Timer");
        stage.getIcons().add(appIcon);
        stage.setScene(scene);
        stage.setMinWidth(800); // Minimum width
        stage.setMinHeight(450); // Minimum height
        stage.setMaxWidth(1080); // Maximum width
        stage.setMaxHeight(650); // Maximum height 650
        stage.show();




    }


    public static void main(String[] args) {
        launch();
    }

}