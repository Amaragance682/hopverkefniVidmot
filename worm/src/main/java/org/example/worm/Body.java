package org.example.worm;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.IOException;

public class Body extends ImageView {
    public Rotate rotation = new Rotate();
    private int numberSegments;

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
