package org.example.worm;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Head {
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
