package org.example.worm;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;

/**
 * We used the FXMLLoader to load the body.fxml file and set it as the root of the Body class.
 * We also added a Rotate object to the Body class to rotate the body.
 * We created a variable called numberSegments to the class to keep track of the number of segments in the worm.
 * We added a constructor to the Body class that takes a Body object as a parameter and copies its layoutX, layoutY, and rotation angle to the new Body object.
 * We added a constructor to the Body class that takes an ImageView object as a parameter and a Rotate object as a parameter and copies its layoutX, layoutY, and rotation angle to the new Body object.
 */
public class Body extends ImageView {
    /**
     * The Rotation.
     */
    public Rotate rotation = new Rotate();
    private int numberSegments;

    /**
     * Instantiates a new Body.
     */
    public Body() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/body.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new Body.
     *
     * @param that the that
     */
    public Body(Body that) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/body.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        numberSegments = Worm.numberSegments;

        this.getTransforms().add(rotation);
        rotation.setPivotY(this.getFitHeight()/2);
        rotation.setPivotX(this.getFitWidth());

        that.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            this.setLayoutX(that.getLayoutX() - (that.getFitWidth() - 10) * Math.cos(Math.toRadians(that.rotation.getAngle())));
        });
        that.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            this.setLayoutY(that.getLayoutY() - (that.getFitWidth() - 10) * Math.sin(Math.toRadians(that.rotation.getAngle())));
        });

        that.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            rotation.setAngle(rotation.getAngle() + (that.rotation.getAngle() - rotation.getAngle())/(40 + numberSegments*30));
        });
    }

    /**
     * The constructor that takes an ImageView object as a parameter and a Rotate object as a parameter.
     *
     * @param head         an ImageView object representing the head of the worm
     * @param headRotation a Rotate object representing the rotation of the head of the worm
     */
    public Body(ImageView head, Rotate headRotation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/body.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getTransforms().add(rotation);
        rotation.setPivotY(this.getFitHeight()/2);
        rotation.setPivotX(this.getFitWidth());

        head.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            this.setLayoutX(head.getLayoutX() - (head.getFitWidth() - 10) * Math.cos(Math.toRadians(headRotation.getAngle())));
        });
        head.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            this.setLayoutY(head.getLayoutY() - (head.getFitWidth() - 10) * Math.sin(Math.toRadians(headRotation.getAngle())));
        });

        head.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            rotation.setAngle(rotation.getAngle() + (headRotation.getAngle() - rotation.getAngle())/(40 + numberSegments*30));
        });
    }
}
