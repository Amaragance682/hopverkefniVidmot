package org.example.worm;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * The class Head represents the head of the worm.
 * It is responsible for loading the FXML file and setting the controller.
 */
public class Head {
    /**
     * The constructor of the Head class.
     * It loads the FXML file and sets the controller.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public Head() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/head.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
