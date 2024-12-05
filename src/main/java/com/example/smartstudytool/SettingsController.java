package com.example.smartstudytool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML private TextField workDurationField;
    @FXML private TextField breakDurationField;

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pomodoro-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Failed to load home.");
        }
    }

    @FXML
    public void saveSettings() {
        try {
            int workDuration = Integer.parseInt(workDurationField.getText());
            int breakDuration = Integer.parseInt(breakDurationField.getText());

            if (workDuration <= 0 || breakDuration <= 0) {
                throw new NumberFormatException("Durations must be positive numbers.");
            }
            Config config = Config.getInstance();
            config.setWorkDuration(workDuration);
            config.setBreakDuration(breakDuration);

            System.out.println("Settings saved successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid positive numbers.");
        }
    }
}
