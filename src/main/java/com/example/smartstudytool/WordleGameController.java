package com.example.smartstudytool;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class WordleGameController {

    private static final int numberOfGuesses = 6;
    private static final int numberOfLetters = 5;
    private final String word = "APPLE";

    private int currentGuessNumber = 0;

    @FXML
    private GridPane wordGrid;

    @FXML
    private TextField inputField;

    @FXML
    private Button submitButton;

    @FXML
    private Text messageText; // New text node for messages

    private Text[][] guessGrid = new Text[numberOfGuesses][numberOfLetters];

    @FXML
    public void initialize() {
        // Placeholders in the Grid
        for (int i = 0; i < numberOfGuesses; i++) {
            for (int j = 0; j < numberOfLetters; j++) {
                Text text = new Text("_");
                guessGrid[i][j] = text;
                wordGrid.add(text, j, i);
            }
        }
        messageText.setText("Start guessing!");
    }

    @FXML
    public void handleSubmit() {
        String guess = inputField.getText().toUpperCase();

        // Validate guess length
        if (guess.length() != numberOfLetters) {
            inputField.clear();
            messageText.setText("Only enter " + numberOfLetters + "-letter words!");
            return;
        }

        // Validate characters
        if (!guess.matches("[A-Z]+")) {
            inputField.clear();
            messageText.setText("Invalid characters! Enter letters only.");
            return;
        }

        processGuess(guess);
        inputField.clear();

        // Check game-over conditions
        if (currentGuessNumber == numberOfGuesses) {
            messageText.setText("Game over! The word was: " + word + "!");
            submitButton.setDisable(true);
        }
    }

    private void processGuess(String guess) {
        for (int j = 0; j < numberOfLetters; j++) {
            char guessedChar = guess.charAt(j);
            String color;

            if (guessedChar == word.charAt(j)) {
                color = "#28a745"; // Green
            } else if (word.contains(String.valueOf(guessedChar))) {
                color = "#ffc107"; // Yellow
            } else {
                color = "#dc3545"; // Red
            }

            guessGrid[currentGuessNumber][j].setText(String.valueOf(guessedChar));

            // Set color using setFill() instead of setStyle()
            guessGrid[currentGuessNumber][j].setFill(javafx.scene.paint.Color.web(color));

            // Optionally, adjust font size if desired
            guessGrid[currentGuessNumber][j].setStyle("-fx-font-size: 18px;");
        }

        currentGuessNumber++;

        if (guess.equals(word)) {
            messageText.setText("You win!");
            submitButton.setDisable(true);
        }
    }


}
