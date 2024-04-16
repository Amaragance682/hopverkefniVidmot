package org.example.worm;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;

/**
 * The class Leikbord is responsible for managing the game board and the worm.
 * It contains a series of objects, such as the worm, the gold coins, a game controller, and a game stats.
 * It also contains the logic for checking collisions and making more gold.
 * The class is responsible for communication between the game controller and the leikbord in following ways:
 * 1. updating the game controller with the score.
 * 2. stopping the game when the player reaches the edge of the board.
 * 3. starting the game when the player clicks the start button.
 * 4. loading the FXML file for the leikbord.
 * 5. cleaning the leikbord when the game is over.
 * 6. creating new gulls when the player collects gold.
 * 7. removing gulls when the player collects gold.
 * 8. extending the worm when the player collects gold.
 * 9. updating the game controller with the score when the player collects gold.
 */
public class Leikbord extends Pane {
    @FXML
    private Worm worm;
    @FXML
    private Pane entityPane;

    private GameController gameController;

    private ObservableList<Gull> gull = FXCollections.observableArrayList();

    /**
     * The constructor for the Leikbord class.
     * It loads the FXML file for the leikbord, sets the game controller, and starts the worm.
     */
    public Leikbord() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/leikbord-view.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method sets the game controller. It is called by the game controller when it starts the game.
     *
     * @param gameController the game controller
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * The start method starts the worm and the gold coins. It is called by the game controller when it starts the game.
     */
    public void start() {
        clean();
        worm.start();
        worm.setLeikbord(this);
    }

    /**
     * The method checks for collisions between the worm and the gold coins. It is called by the game controller every frame.
     *
     * @param headPoint the point of the worm's head
     */
    public void checkCollision(double[] headPoint) {
        if (headPoint[0] > 1400 || headPoint[0] < -100 || headPoint[1] > 800 || headPoint[1] < -100) {
            System.out.println("gamer");
            gameController.getStats().stop();
        }
        Gull got = null;
        for (Gull g : gull) {
            if (Math.abs(g.getLayoutX() - headPoint[0]) < 50 && Math.abs(g.getLayoutY() - headPoint[1]) < 50) {
                gotGold(g);
                got = g;
            }
        }
        if (got != null) {
            gull.remove(got);
            worm.enlarge();
        }
    }


    /**
     * The method is called when the player collects gold. It updates the score and removes the gold.
     *
     * @param g the gold coin that was collected
     *
     */
    private void gotGold(Gull g) {
        entityPane.getChildren().remove(g);
        GameController.points.set(GameController.points.get()+10);

    }

    /**
     * The method is called when the player collects gold. It creates a new gold coin and adds it to the leikbord.
     */
    public void makeMoreGold() {
        Random random = new Random();

        Gull gull1 = new Gull();
        gull1.setLayoutX(random.nextInt(1260) - 10.0);
        gull1.setLayoutY(random.nextInt(660) + 20.0);
        gull.add(gull1);
        entityPane.getChildren().add(gull1);
    }

    /**
     * The method is called when the game is over. It cleans the leikbord and stops the worm.
     */
    private void clean() {
        gull.clear();
        entityPane.getChildren().removeIf(e -> e.getClass() != Worm.class);
        this.getChildren().removeIf(e -> e.getClass() != Pane.class && e.getClass() != ImageView.class);
    }
}
