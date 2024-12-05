package com.example.smartstudytool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PomodoroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(PomodoroApplication.class.getResource("pomodoro-view.fxml"));
        Scene scene = new Scene(loader.load(), 320, 400);

        stage.setTitle("Pomodoro Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}