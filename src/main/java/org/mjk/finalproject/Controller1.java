package org.mjk.finalproject;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller1 {
    int brewTime = 15; //default total time in seconds
    //audio
   // File TeaDone = new File("src/main/resources/org/mjk/finalproject/TeaDone.m4a");
  //  File TeaWarning = new File("src/main/resources/org/mjk/finalproject/WarningTea.m4a");
  //  Media media = new Media(TeaDone.toURI().toString());
 //   MediaPlayer mediaPl = new MediaPlayer(media);
    String teaDonePath = "/org/mjk/finalproject/TeaDone.m4a";
    String warningTeaPath = "/org/mjk/finalproject/WarningTea.m4a";
    Media teaDone = new Media(getClass().getResource(teaDonePath).toString());
    Media warningTea = new Media(getClass().getResource(warningTeaPath).toString());
    MediaPlayer mediaPl = new MediaPlayer(teaDone);
    MediaPlayer mediaPl2 = new MediaPlayer(warningTea);
   // File teaDoneFile = new File("src/main/resources/org/mjk/finalproject/TeaDone.mp3");
   // File teaWarningFile = new File("src/main/resources/org/mjk/finalproject/WarningTea.mp3");
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
    private Label teaAmount;
    @FXML
    private SVGPath teaSvg;
    private int timeMilliseconds = brewTime * 1000; // Convert seconds to ms
    private Timeline timeline; //Init timeline for countdown
    private int infusionCounter = 1;
    private int infusions;
    private int nextInfusionDuration;
    //Add borderpane functionality (drag, pressed)
    private double borderX = 0;
    private double borderY = 0;

    //methods
    @FXML
    private void borderpane_dragged(MouseEvent event) { //Move borderpane when dragged
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.setY(event.getScreenY() - borderY);
        stage.setX(event.getScreenX() - borderX);
    }

    @FXML
    private void borderpane_pressed(MouseEvent event) { //sets starting point based off mouseclick
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
    private void handleGreenTeaButton() {
        updateTeaInfo("Green", 15, 3, 5, "3 - 3.5");

    }

    @FXML
    private void handleBlackSmallLeafTeaButton() {
        updateTeaInfo("Black (small leaf)", 10, 5, 8, "4.5");
    }

    @FXML
    private void handleBlackLargeLeafTeaButton() {
        updateTeaInfo("Black (large leaf)", 15, 5, 8, "4");
    }

    @FXML
    private void handleWhiteTeaButton() {
        updateTeaInfo("White", 20, 5, 5, "3.5 - 4");
    }

    @FXML
    private void handleYellowTeaButton() {
        updateTeaInfo("Yellow", 15, 5, 5, "3.5 - 4");
    }

    @FXML
    private void handleOolongStripTeaButton() {
        updateTeaInfo("Oolong (strip)", 20, 5, 9, "4.5 - 5");
    }

    @FXML
    private void handleOolongBallTeaButton() {
        updateTeaInfo("Oolong (ball)", 25, 5, 9, "6 - 6.5");
    }

    @FXML
    private void handlePuErhRawTeaButton() {
        updateTeaInfo("PuErh (raw)", 10, 3, 15, "5");
    }

    @FXML
    private void handlePuErhRipeTeaButton() {
        updateTeaInfo("PuErh (ripe)", 10, 5, 20, "5");
    }

    //Previous and Skip Functionality
    @FXML
    private void handlePrevious() {
        if (infusionCounter > 1) { //I am choosing to allow the user to oversteep the tea, as I sometimes do the same
            infusionCounter--;
            infusionCounts.setText(("Infusions: ") + infusionCounter + " / " + infusions);

            System.out.println("Before Subtraction - brewTime: " + brewTime + ", nextInfusionDuration: " + nextInfusionDuration);
            brewTime -= nextInfusionDuration; // Subtraction here
            timeMilliseconds = brewTime * 1000;
            System.out.println("After Subtraction - brewTime: " + brewTime);

        }

    }

    @FXML
    private void handleSkip() {
        if (infusionCounter >= 1) { //I am choosing to allow the user to oversteep the tea, as I sometimes do the same
            infusionCounter++;
            infusionCounts.setText(("Infusions: ") + infusionCounter + " / " + infusions);

            System.out.println("Before Subtraction - brewTime: " + brewTime + ", nextInfusionDuration: " + nextInfusionDuration);
            brewTime += nextInfusionDuration; // Subtraction here
            timeMilliseconds = brewTime * 1000;
            System.out.println("After Subtraction - brewTime: " + brewTime);

        }
    }

    //logic for pressing buttons
    private void updateTeaInfo(String teaName, int firstInfusion, int nextInfusion, int infusions, String amounts) {  //represents grams needed
        //Check if timer is still running and stop it if it is
        this.infusions = infusions;
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }

        STARTbtn.setText("Start");
        teaType.setText("00:00:000");

        // Update the teaTypeLabel to display the selected tea type
        typeLabel.setText(teaName);
        brewTime = firstInfusion;
        teaAmount.setText(amounts);
        nextInfusionDuration = nextInfusion; //this allows the variable to be used outside of this scope
        infusionCounter = 1;
        infusionCounts.setText(("Infusions: ") + infusionCounter + " / " + infusions);

        timeMilliseconds = brewTime * 1000; //Resets time allowing user to change teaType while paused
    }

    @FXML
    private void handleStartButton() { // this comes from the onAction of the buttons fxml
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
                applyShakeAnimation(teaSvg);

                //increment infusions
                infusionCounter++;
                int infusions = Integer.parseInt(infusionCounts.getText().split(" / ")[1]);
                infusionCounts.setText("Infusions: " + infusionCounter + " / " + infusions);

                //update brewtime
                if (infusionCounter <= infusions) {
                    brewTime += nextInfusionDuration;
                }
                timeMilliseconds = brewTime * 1000; // Reset timeMilliseconds for the new brewTime
            }
            if (timeMilliseconds == 5000) {
                mediaPl2.seek(Duration.ZERO); // this pretty much resets to start
                mediaPl2.play();
            }
        }));
        timeline.setCycleCount(brewTime * 1000); // Repeat indefinitely
        timeline.play();
    }

    //Animate Tea Icon
    private void applyShakeAnimation(SVGPath teaSvg) {
        double originalRotate = teaSvg.getRotate(); // Store the original rotation
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.1), teaSvg);
        rotateTransition.setFromAngle(-5);
        rotateTransition.setToAngle(5);
        rotateTransition.setCycleCount(5); // Number of times it shakes
        rotateTransition.setAutoReverse(true); // Back and forth shake motion
        rotateTransition.setInterpolator(Interpolator.LINEAR); // Linear interpolation for smooth motion
        rotateTransition.play();
        rotateTransition.setOnFinished(event -> {
            teaSvg.setRotate(originalRotate); // Reset to original rotation
        });
    }

}
