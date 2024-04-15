package org.example.worm;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;


public class GameController {
    @FXML
    private Leikbord leikbord;

    @FXML
    private Stats stats;

    public static SimpleIntegerProperty points = new SimpleIntegerProperty(0);

    private Timeline goldTimeline;

    public void newGame() {
        stop();
        stats.makeClock();
        stats.bindLabels();
        makeGold();
        leikbord.start();
        points.set(0);
    }


    private void makeGold() {
        goldTimeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> {
            leikbord.makeMoreGold();
        }));
        goldTimeline.setCycleCount(Animation.INDEFINITE);
        goldTimeline.play();
    }

    public void stop() {
        if (goldTimeline != null) {
            goldTimeline.stop();
        }
    }

    public void endScreen() {
        if (points.get() < 50) {
            failure();
        } else {
            victory();
        }
    }

    private Rectangle makeRectangle(Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(700);
        rectangle.setWidth(1300);
        rectangle.setFill(color);
        rectangle.setOpacity(0.0);
        return rectangle;
    }

    private void failure() {
        Rectangle blackRectangle = makeRectangle(BLACK);
        leikbord.getChildren().add(blackRectangle);
        Timeline failTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            blackRectangle.setOpacity(blackRectangle.getOpacity() + 0.01);
        }));
        failTimeline.setCycleCount(120);
        failTimeline.play();
        failTimeline.setOnFinished(e -> {
            Label failLabel = new Label();
            failLabel.setText("failure...");
            failLabel.setFont(new Font("System Bold", 48));
            failLabel.setTextFill(WHITE);
            failLabel.setLayoutX(570);
            failLabel.setLayoutY(290);
            leikbord.getChildren().add(failLabel);
        });
    }

    private void victory() {
        Rectangle whiteRectangle = makeRectangle(WHITE);
        leikbord.getChildren().add(whiteRectangle);
        Timeline victoryTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            whiteRectangle.setOpacity(whiteRectangle.getOpacity() + 0.01);
        }));
        victoryTimeline.setCycleCount(120);
        victoryTimeline.play();
        victoryTimeline.setOnFinished(e -> {
            Label winLabel = new Label();
            winLabel.setText("VICTORY!!!");
            winLabel.setFont(new Font("System Bold", 48));
            winLabel.setTextFill(BLACK);
            winLabel.setLayoutX(530);
            winLabel.setLayoutY(290);
            leikbord.getChildren().add(winLabel);
        });
    }
    public void setDifficulty(int i) {
        stats.setDifficulty(i);
    }

    public Stats getStats() {
        return stats;
    }

    public void initialize() {
        stats.setGameController(this);
        leikbord.setGameController(this);
    }
}
