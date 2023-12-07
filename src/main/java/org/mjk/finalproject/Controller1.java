package org.mjk.finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;
//Import Audio
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.util.ArrayList;
import java.util.List;

public class Controller1 {
//              Add the fxml elements to controller file
    @FXML
    private Label teaType;
    @FXML
    private Button STARTbtn; //start button
    @FXML
    private Label typeLabel; //This label indicates tea type in center of screen
    @FXML
    private Label infusionCounts;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button previous;
    @FXML
    private Button skip;
    int brewTime = 15; //default total time in seconds
    private int timeMilliseconds = brewTime * 1000; // Convert seconds to ms
    private Timeline timeline; //Init timeline for countdown
    private int infusionCounter = 1;
    private int infusions;
    private int nextInfusionDuration;

    //Add borderpane functionality (drag, pressed)
    private double borderX = 0;
    private double borderY = 0;

    //audio


    File TeaDone = new File("src/main/resources/org/mjk/finalproject/TeaDone.m4a");
    File TeaWarning = new File("src/main/resources/org/mjk/finalproject/WarningTea.m4a");
    Media media = new Media(TeaDone.toURI().toString());
    Media media2 = new Media(TeaWarning.toURI().toString());
    MediaPlayer mediaPl = new MediaPlayer(media);
    MediaPlayer mediaPl2 = new MediaPlayer(media2);



    File teaDoneFile = new File("src/main/resources/org/mjk/finalproject/TeaDone.mp3");
    File teaWarningFile = new File("src/main/resources/org/mjk/finalproject/WarningTea.mp3");

    //methods
    @FXML
    private void borderpane_dragged(MouseEvent event){ //Move borderpane when dragged
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.setY(event.getScreenY() - borderY);
        stage.setX(event.getScreenX() - borderX);
    }

    @FXML
    private void borderpane_pressed(MouseEvent event){ //sets starting point based off mouseclick
        borderX = event.getSceneX();
        borderY = event.getSceneY();
    }

    //Exit functionality
    public void closeApp(MouseEvent mouseEvent) { //making this public might be an issue IDK, I had to get the onpressed to work
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }                                   //These SVGs are hard to press, might want to use imageview or put a square ontop of
    public void minimizeApp(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.setIconified(true);
    }

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

    //Previous and Skip Functionality
    @FXML
    private void handlePrevious(ActionEvent event){
        if( infusionCounter > 1){ //I am choosing to allow the user to oversteep the tea, as I sometimes do the same
            infusionCounter--;
            infusionCounts.setText(("Infusions: ")+ infusionCounter + " / " + infusions);

            System.out.println("Before Subtraction - brewTime: " + brewTime + ", nextInfusionDuration: " + nextInfusionDuration);
            brewTime -= nextInfusionDuration; // Subtraction here
            timeMilliseconds = brewTime * 1000;
            System.out.println("After Subtraction - brewTime: " + brewTime);

        }

    }
    @FXML
    private void handleSkip(ActionEvent event){
        if( infusionCounter >= 1){ //I am choosing to allow the user to oversteep the tea, as I sometimes do the same
            infusionCounter++;
            infusionCounts.setText(("Infusions: ")+ infusionCounter + " / " + infusions);

            System.out.println("Before Subtraction - brewTime: " + brewTime + ", nextInfusionDuration: " + nextInfusionDuration);
            brewTime += nextInfusionDuration; // Subtraction here
            timeMilliseconds = brewTime * 1000;
            System.out.println("After Subtraction - brewTime: " + brewTime);

        }
    }


    //logic for pressing buttons
    private void updateTeaInfo(String teaName, int firstInfusion, int nextInfusion, int infusions, List<Double> amounts) { //List represents grams needed: not incorporated yet
        //Check if timer is still running and stop it if it is
       this.infusions = infusions;
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
           // STARTbtn.setText("Start");
         //   teaType.setText("00:00:000");
        }

        STARTbtn.setText("Start");
        teaType.setText("00:00:000");



        // Update the teaTypeLabel to display the selected tea type
        typeLabel.setText(teaName);
        brewTime = firstInfusion;
        nextInfusionDuration = nextInfusion; //this allows the variable to be used outside of this scope
        infusionCounter = 1;
        infusionCounts.setText(("Infusions: ")+ infusionCounter + " / " + infusions);

        timeMilliseconds = brewTime * 1000; //Resets time allowing user to change teaType while paused
    }


        @FXML
        private void handleStartButton (ActionEvent event){ // this comes from the onAction of the buttons fxml
            if (timeline == null) {
                startTimer(); //start the timer because not alr running
                STARTbtn.setText("Pause");
            } else {
                if (timeline.getStatus() == Animation.Status.RUNNING) { //if running and pressed change to resume
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
              //DEBUG  System.out.println(timerDisplay);
                teaType.setText(timerDisplay); //Format the label
                if (timeMilliseconds <= 0) { //  Add logic for when the timer reaches 0
                    timeline.stop();
                    mediaPl.seek(Duration.ZERO); // this pretty much resets to start
                    mediaPl.play();
                    STARTbtn.setText("Start"); // Change button back to start



                    //increment infusions
                    infusionCounter++;
                    int infusions = Integer.parseInt(infusionCounts.getText().split(" / ")[1]);
                    infusionCounts.setText("Infusions: " + infusionCounter + " / " + infusions);

                    //update brewtime
                    if(infusionCounter <= infusions){
                        brewTime += nextInfusionDuration;
                    }


                    timeMilliseconds = brewTime * 1000; // Reset timeMilliseconds for the new brewTime
                }
                if(timeMilliseconds == 5000){
                    mediaPl2.seek(Duration.ZERO); // this pretty much resets to start
                    mediaPl2.play();
                }
            }));
            timeline.setCycleCount(brewTime * 1000); // Repeat indefinitely
            timeline.play();

        }



}
