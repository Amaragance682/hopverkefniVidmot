package org.example.worm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import vinnsla.Klukka;

import java.io.IOException;

/**
 * The class Stats. It contains the GUI elements for displaying the game statistics.
 * It also contains the logic for the timer and the score.
 * Difficulty stands for the time limit of the game, it starts with 35 seconds and decreases by 1 second for each level.
 */
public class Stats extends AnchorPane {
    @FXML
    private Label timer;

    @FXML
    private Label score;

    private GameController gameController;

    private int difficulty = 35;

    private Klukka klukka;

    private Timeline timeTimeline;

    /**
     * The constructor of the class.
     * It initializes the GUI elements and sets the difficulty to 35.
     */
    public Stats() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/stats.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method makeClock. It creates a new Klukka object that is specified for the difficulty and starts a timeline with the time limit.
     */
    public void makeClock() {
        if (timeTimeline != null) {
            timeTimeline.stop();
        }
        System.out.println(difficulty);
        klukka = new Klukka(difficulty);
        timer.setText(Integer.toString(difficulty));
        timeTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            System.out.println("tick");
            klukka.tick();
            timer.setText(Integer.toString(klukka.getTime()));
        }));
        timeTimeline.setCycleCount(difficulty);
        timeTimeline.setOnFinished(e -> stop());
        timeTimeline.play();
    }

    /**
     * The method bindLabels. It binds the score label to the points variable (score) in the GameController class.
     */
    public void bindLabels() {
        score.textProperty().bind(GameController.points.asString());
    }


    /**
     * The method stop. It stops the timer and resets the Klukka object and the gameController.
     */
    public void stop() {
        if (timeTimeline != null) {
            timeTimeline.stop();
            klukka.reset();
            gameController.stop();
            gameController.endScreen();
        }
    }

    /**
     * The method setGameController. It sets the gameController variable.
     *
     * @param gameController specifies the gameController object.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Sets the difficulty.
     *
     * @param i the time allowed for each single gameplay.
     */
    public void setDifficulty(int i) {
        this.difficulty = i;
    }
}
