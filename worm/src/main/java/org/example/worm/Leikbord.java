package org.example.worm;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;

public class Leikbord extends Pane {
    @FXML
    private Worm worm;
    @FXML
    private Pane entityPane;

    private GameController gameController;

    private ObservableList<Gull> gull = FXCollections.observableArrayList();

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

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void start() {
        clean();
        worm.start();
        worm.setLeikbord(this);
    }

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

    private void gotGold(Gull g) {
        entityPane.getChildren().remove(g);
        GameController.points.set(GameController.points.get()+10);

    }

    public void makeMoreGold() {
        Random random = new Random();

        Gull gull1 = new Gull();
        gull1.setLayoutX(random.nextInt(1260) - 10.0);
        gull1.setLayoutY(random.nextInt(660) + 20.0);
        gull.add(gull1);
        entityPane.getChildren().add(gull1);
    }

    private void clean() {
        gull.clear();
        entityPane.getChildren().removeIf(e -> e.getClass() != Worm.class);
        this.getChildren().removeIf(e -> e.getClass() != Pane.class && e.getClass() != ImageView.class);
    }
}
