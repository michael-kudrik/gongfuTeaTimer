package org.mjk.finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Controller1 {

    @FXML
    private Label teaType;
    @FXML
    private Button STARTbtn;

    int brewTime = 15;
    private int timeSeconds = brewTime; // Initial time in seconds
    private Timeline timeline;

    public void initialize() {
    }

    @FXML
    private void handleStartButton(ActionEvent event) {
        startTimer();
    }

    private void startTimer() {
        System.out.println("Timer started");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeSeconds--;
            teaType.setText(Integer.toString(timeSeconds));
            if (timeSeconds <= 0) {
                timeline.stop();
                timeSeconds = brewTime;
                //  Add logic for when the timer reaches 0
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();

}
    }