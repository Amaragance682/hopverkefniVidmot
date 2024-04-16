package org.example.worm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Create a type of game application that allows the user to play the game.
 * The class Worm is to create the object that the player can control in the gameplay.
 * In the Stage object, the FXMLLoader is used to load the main-view.fxml file, which contains the gameplay interface.
 */
public class GameApplication extends Application {

    private Worm worm;
    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource("/org/example/worm/main-view.fxml"));
        Scene scene = new Scene(loader.load(), 1300, 700);

        primaryStage.setTitle("Only sigmas allowed");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
