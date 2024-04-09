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

public class Stats extends AnchorPane {
    @FXML
    private Label timer;

    @FXML
    private Label score;

    private GameController gameController;

    private int difficulty = 35;

    private Klukka klukka;

    private Timeline timeTimeline;

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

    public void bindLabels() {
        score.textProperty().bind(GameController.points.asString());
    }


    private void stop() {
        if (timeTimeline != null) {
            timeTimeline.stop();
            klukka.reset();
            gameController.stop();
            gameController.endScreen();
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setDifficulty(int i) {
        this.difficulty = i;
    }
}
