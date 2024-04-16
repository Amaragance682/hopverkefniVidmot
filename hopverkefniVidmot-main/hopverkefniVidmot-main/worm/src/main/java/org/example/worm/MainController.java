package org.example.worm;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * The class MainController is responsible for handling the main menu and gameplay.
 * It is responsible for loading the FXML files, setting up the menu and game controllers, and playing the music.
 * It also handles the events of the buttons in the menu and gameplay.
 */
public class MainController {

    private MediaPlayer mediaPlayer;

    @FXML
    private MenuController menuController;
    @FXML
    private GameController gameController;

    @FXML
    private Leikbord leikbord;


    /**
     * The method handlePlay is called when the user clicks New Game button in the menu.
     * The method plays the music and switches to the gameplay screen.
     * @param event the even when the button is clicked.
     */
    @FXML
    public void handlePlay(ActionEvent event) {
        playMusic("/assets/theme.mp3");
    }

    /**
     * See the explanation of the method from the comments below.
     */
    @FXML
    public void initialize() {
        // This method is automatically called after the FXML fields have been injected.
        playMusic("/assets/theme.mp3"); // Directly call playMusic() here
        menuController.setGameController(gameController);

    }
    /**
     * The method playMusic is responsible for playing the music.
     * It first checks if the mediaPlayer is null, and if it is, it creates a new MediaPlayer object with the given resource path.
     * It then plays the mediaPlayer.
     * @param resourcePath the path that leads to the music file in the project folder.
     */
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