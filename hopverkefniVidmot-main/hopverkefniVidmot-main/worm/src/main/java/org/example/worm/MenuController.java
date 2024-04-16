package org.example.worm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioMenuItem;
/**
 * Controls the main menu functionalities for the Gold Rush game.
 * This class handles user actions from the menu, including starting a new game,
 * quitting the application, displaying help information, and changing the difficulty level.
 */
public class MenuController {
    private GameController gameController; // make connection to game controller


    @FXML
    private void handleNewGame() {
        gameController.newGame();
    }
    @FXML
    private void handleQuit() {
        Platform.exit();
    }

    @FXML
    private void handleShowHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to play:");
        alert.setHeaderText(null); // You can set a header text here
        alert.setContentText("Move your character around the map and try to find as many golden goins before the time runs out!");

        alert.showAndWait(); // Show the dialog and wait for it to be closed
    }

    /**
     * Handles the difficulty change radio buttons.
     * @param e when the user selects a difficulty level radio button.
     */
    @FXML
    private void handleDifficultyChange(ActionEvent e) {
        RadioMenuItem jeff = (RadioMenuItem) e.getSource();
        gameController.setDifficulty(Integer.parseInt(jeff.getId()));
    }


    /**
     * Sets the game controller for this class.
     * @param gameController the game controller to set.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;

    }


    @FXML
    private void initialize() {
        // fylgjast eftir difficulty change og ef leikur er byrjadur
    }

}
