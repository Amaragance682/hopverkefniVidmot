package org.example.worm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;




public class GameApplication extends Application {

    private Worm worm;
    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource("/org/example/worm/main-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 600);

        primaryStage.setTitle("Only sigmas allowed");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
