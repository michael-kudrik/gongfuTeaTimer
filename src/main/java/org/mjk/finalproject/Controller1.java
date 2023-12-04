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

import java.util.ArrayList;
import java.util.List;

public class Controller1 {

    @FXML
    private Label teaType;
    @FXML
    private Button STARTbtn; //start button
    @FXML
    private Label typeLabel; //This label indicates tea type in center of screen
    int brewTime = 15; //default total time in seconds
    private int timeMilliseconds = brewTime * 1000; // Convert seconds to ms
    private Timeline timeline; //Init timeline for countdown

    public void initialize() {

    }

    //                                      HANDLE EACH TEA TYPE PRESS

    @FXML
    private void handleGreenTeaButton(ActionEvent event) {
        updateTeaInfo("Green", 15, 3, 5, List.of(3.0, 3.5)); //List represents grams needed: not incorporated yet

    }

    @FXML
    private void handleBlackSmallLeafTeaButton(ActionEvent event) {
        updateTeaInfo("Black (small leaf)", 10, 5, 8, List.of(4.5));
    }

    @FXML
    private void handleBlackLargeLeafTeaButton(ActionEvent event) {
        updateTeaInfo("Black (large leaf)", 15, 5, 8, List.of(4.0));
    }

    @FXML
    private void handleWhiteTeaButton(ActionEvent event) {
        updateTeaInfo("White", 20, 5, 5, List.of(3.5, 4.0));
    }

    @FXML
    private void handleYellowTeaButton(ActionEvent event) {
        updateTeaInfo("Yellow", 15, 5, 5, List.of(3.5, 4.0));
    }

    @FXML
    private void handleOolongStripTeaButton(ActionEvent event) {
        updateTeaInfo("Oolong (strip)", 20, 5, 9, List.of(4.5, 5.0));
    }

    @FXML
    private void handleOolongBallTeaButton(ActionEvent event) {
        updateTeaInfo("Oolong (ball)", 25, 5, 9, List.of(6.0, 6.5));
    }

    @FXML
    private void handlePuErhRawTeaButton(ActionEvent event) {
        updateTeaInfo("PuErh (raw)", 10, 3, 15, List.of(5.0));
    }

    @FXML
    private void handlePuErhRipeTeaButton(ActionEvent event) {
        updateTeaInfo("PuErh (ripe)", 10, 5, 20, List.of(5.0));
    }


    //logic for pressing buttons
    private void updateTeaInfo(String teaName, int firstInfusion, int nextInfusion, int infusions, List<Double> amounts) { //List represents grams needed: not incorporated yet
        // Update the teaTypeLabel to display the selected tea type
        typeLabel.setText(teaName);
        brewTime = firstInfusion;
    }


        @FXML
        private void handleStartButton (ActionEvent event){ // this comes from the onAction of the buttons fxml
            if (timeline == null) {
                startTimer(); //start the timer because not alr running
                STARTbtn.setText("Pause");
            } else {
                if (timeline.getStatus() == Animation.Status.RUNNING) { //if running and presed change to resume
                    timeline.pause();
                    STARTbtn.setText("Resume");
                } else { // if not running resume and change to pause
                    timeline.play();
                    STARTbtn.setText("Pause");
                }
            }

        }

        private void startTimer() {
            System.out.println("Timer started");
            timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
                timeMilliseconds--;
                int minutes = timeMilliseconds / (60 * 1000);
                int seconds = (timeMilliseconds % (60 * 1000)) / 1000;
                int milliseconds = timeMilliseconds % 1000;
                String timerDisplay = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
                System.out.println(timerDisplay);
                teaType.setText(timerDisplay); //Format the label
                if (timeMilliseconds <= 0) {
                    timeline.stop();
                    timeMilliseconds = brewTime * 1000;
                    STARTbtn.setText("Start"); // Change button back to start
                    //  Add logic for when the timer reaches 0
                }
            }));
            timeline.setCycleCount(brewTime * 1000); // Repeat indefinitely
            timeline.play();

        }






    }
