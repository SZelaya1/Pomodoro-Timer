package com.example.smartstudytool;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class PomodoroController {
    @FXML private Label timerLabel;
    @FXML private Label statusLabel;
    @FXML private Label sessionsLabel;
    @FXML private Button startButton;
    @FXML private Button pauseButton;
    private int workDuration;
    private int breakDuration;
    private int secondsLeft;
    private boolean isWorking = true;
    private int completedSessions = 0;
    private Timeline timer;

    @FXML
    public void initialize() {
        Config config = Config.getInstance();
        workDuration = config.getWorkDuration();
        breakDuration = config.getBreakDuration();

        secondsLeft = workDuration * 60;
        updateTimerLabel();

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsLeft--;
            updateTimerLabel();
            if (secondsLeft <= 0) {
                handleTimerEnd();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);

        pauseButton.setDisable(true);
    }

    @FXML
    private void handleStart() {
        timer.play();
        statusLabel.setText(isWorking ? "Status: Working" : "Status: Break");
        startButton.setDisable(true);
        pauseButton.setDisable(false);
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Failed to load settings.");
        }
    }

    @FXML
    private void handlePause() {
        timer.pause();
        startButton.setDisable(false);
        pauseButton.setDisable(true);
    }

    @FXML
    private void handleReset() {
        timer.stop();
        secondsLeft = isWorking ? workDuration * 60 : breakDuration * 60;
        updateTimerLabel();
        statusLabel.setText("Status: Ready");
        startButton.setDisable(false);
        pauseButton.setDisable(true);
    }

    private void updateTimerLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void handleTimerEnd() {
        isWorking = !isWorking;
        if (isWorking) {
            completedSessions++;
            sessionsLabel.setText("Sessions Completed: " + completedSessions);
        }
        secondsLeft = isWorking ? workDuration * 60 : breakDuration * 60;
        updateTimerLabel();
        statusLabel.setText(isWorking ? "Status: Working" : "Status: Break");
    }
}
