package org.example.worm;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
/**
 * Represents a gold object in the Gold Rush game.
 * This class encapsulates the functionality for loading the visual representation of a gold piece
 * and provides access to its bounds for collision detection and other purposes.
 */
public class Gull extends AnchorPane {
    @FXML
    private ImageView gold;
    /**
     * Constructs a new Gull object by loading its FXML layout.
     * The layout file specifies the visual structure of the gold piece,
     * including its image and any effects applied to it.
     */
    public Gull() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/gull-view.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            System.out.println("gull made");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Gets the bounds of the gold ImageView within the parent node.
     * This method is useful for collision detection, allowing the game to determine
     * when the player has collected this gold piece.
     *
     * @return The bounds of the gold ImageView in the parent node's coordinate space.
     */
    public Bounds getGoldBounds() {
        return gold.getBoundsInParent();
    }
}
