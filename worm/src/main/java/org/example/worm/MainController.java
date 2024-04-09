package org.example.worm;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MainController {

    private MediaPlayer mediaPlayer;

    @FXML
    private MenuController menuController;
    @FXML
    private GameController gameController;

    @FXML
    private Leikbord leikbord;


    @FXML
    public void handlePlay(ActionEvent event) {
        playMusic("/assets/theme.mp3");
    }

    @FXML
    public void initialize() {
        // This method is automatically called after the FXML fields have been injected.
        playMusic("/assets/theme.mp3"); // Directly call playMusic() here
        menuController.setGameController(gameController);

    }
    private void playMusic(String resourcePath) {

        if (mediaPlayer == null) {
            URL resourceURL = getClass().getResource(resourcePath);
            if (resourceURL == null) {
                throw new IllegalArgumentException("Cannot find file: " + resourcePath);
            }
            String audioFilePath = resourceURL.toExternalForm();
            Media audioFile = new Media(audioFilePath);
            mediaPlayer = new MediaPlayer(audioFile);
        }
        mediaPlayer.play();
    }





/*
    public AudioPlayer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/mp3Player.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    // Add other control methods here (pause, stop, etc.) if needed
}