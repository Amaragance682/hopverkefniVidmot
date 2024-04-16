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


/**
 * The class created for the player to be able to control the snake / dragon.
 * It contains the Leikbord and Stats classes, which are responsible for the game logic and parts of the visuals.
 * The class Leikbord is responsible for the snake movement, and the Stats class is responsible for the score and time logic.
 */
public class GameController {
    @FXML
    private Leikbord leikbord;

    @FXML
    private Stats stats;

    /**
     * The points property sets the score of the player, and it is initialized to 0 for each new game.
     * The goldTimeline is responsible for the creation of gold coin every 3 seconds.
     */
    public static SimpleIntegerProperty points = new SimpleIntegerProperty(0);

    private Timeline goldTimeline;

    /**
     * This method initiates a new game by resetting the score, the clock, the gold coins, and starting the snake movement.
     */
    public void newGame() {
        stop();
        stats.makeClock();
        stats.bindLabels();
        makeGold();
        leikbord.start();
        points.set(0);
    }

    /**
     * The private makeGold method controls the generation of a new gold coin every 3 seconds.
     * It creates a new Rectangle object and adds it to the Leikbord class, which is responsible for the snake movement.
     * The goldTimeline is set to run non-stop for the duration of the gameplay.
     * The goldTimeline is stopped when the game is over.
     */
    private void makeGold() {
        goldTimeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> {
            leikbord.makeMoreGold();
        }));
        goldTimeline.setCycleCount(Animation.INDEFINITE);
        goldTimeline.play();
    }

    /**
     * the stop method stops the goldTimeline if it is running.
     * This method is called when the game is over.
     */
    public void stop() {
        if (goldTimeline != null) {
            goldTimeline.stop();
        }
    }

    /**
     * The endScreen method is called when the game is over.
     * The method checks if the player has won or lost, and displays a message accordingly.
     * If the player has gained less than 50 points, the failure method is called.
     * Otherwise, the victory method is called.
     */
    public void endScreen() {
        if (points.get() < 50) {
            failure();
        } else {
            victory();
        }
    }

    /**
     * The makeRectangle method creates a new Rectangle object with a specified color.
     * It is used to create a black or white rectangle for the end screen.
     *
     * @param color stands for the color of the rectangle displayed on the end screen.
     * @return a Rectangle object
     * below you can see the size and color of the makeRectangle method:
     */
    private Rectangle makeRectangle(Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(700);
        rectangle.setWidth(1300);
        rectangle.setFill(color);
        rectangle.setOpacity(0.0);
        return rectangle;
    }

    /**
     * The failure method is called when the player has less than 50 points.
     * It creates a black rectangle and displays a failure message (in white font) on the end screen.
     * The failure message is displayed for 120 cycles of the timeline, with a 20ms delay between each cycle.
     * The timeline is played non-stop for the duration of the gameplay.
     * The timeline is stopped when the game is over.
     * The method is called from the endScreen method.
     * The label is positioned in the center of the screen.
     */
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

    /**
     * The victory method is called when the player has gained at least 50 points.
     * It creates a white rectangle and displays a victory message (in black font) on the end screen.
     * The victory message is displayed for 120 cycles of the timeline, with a 20ms delay between each cycle.
     * The timeline is played non-stop for the duration of the gameplay.
     * The timeline is stopped when the game is over.
     * The method is called from the endScreen method.
     * The label is positioned in the center of the screen.
     */
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

    /**
     * The setDifficulty method sets the difficulty level of the game.
     *
     * @param i the difficulty level where i presents the amount of time of the gameplay.
     */
    public void setDifficulty(int i) {
        stats.setDifficulty(i);
    }

    /**
     * This getter method returns stats.
     *
     * @return the stats class
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * The initialize method is called when the FXML file is loaded.
     * It sets the gameController of the Stats and Leikbord classes to this class.
     */
    public void initialize() {
        stats.setGameController(this);
        leikbord.setGameController(this);
    }
}
