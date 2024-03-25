package org.example.worm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import vinnsla.VectorCalc;

public class HelloController {
    @FXML
    private Rectangle player;
    private Rotate rotation = new Rotate();
    private double deltaRotation;

    @FXML
    private Rectangle body;
    private Rotate bodyRotation = new Rotate();
    private double bodyDeltaRotation;

    @FXML
    private Rectangle tail;
    private Rotate tailRotation = new Rotate();

    private boolean left;
    private boolean right;

    private enum Direction {
        LEFT,RIGHT,FORWARD;
    }

    private Direction direction;

    @FXML
    protected void movementPressHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                left=true;
                break;
            case D:
                right=true;
                break;
            case LEFT:
                left=true;
                break;
            case RIGHT:
                right=true;
                break;
            default:
                break;
        }
    }

    @FXML
    protected void movementReleaseHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                left=false;
                break;
            case D:
                right=false;
                break;
            case LEFT:
                left=false;
                break;
            case RIGHT:
                right=false;
                break;
            default:
                break;
        }
    }

    private void updateDirection() {
        if (left && right) {
            direction = Direction.FORWARD;
        } else if (left) {
            direction = Direction.LEFT;
        } else if (right) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.FORWARD;
        }
    }

    private void rotateHead() {
        if (direction == Direction.FORWARD) {
            if (deltaRotation > 0) deltaRotation -= deltaRotation/5;
            if (deltaRotation < 0) deltaRotation += Math.abs(deltaRotation)/5;
            return;
        }
        if (direction == Direction.RIGHT) {
            rotation.setAngle(rotation.getAngle() + 2);
            if (deltaRotation < 3) deltaRotation += 1.0/5.0;
        }
        if (direction == Direction.LEFT) {
            rotation.setAngle(rotation.getAngle() - 2);
            if (deltaRotation > -3) deltaRotation -= 1.0/5.0;
        }
    }

    private void moveHead() {
        player.setLayoutX(player.getLayoutX() + 4*VectorCalc.calcMoveX(rotation.getAngle()));
        player.setLayoutY(player.getLayoutY() + 5*VectorCalc.calcMoveY(rotation.getAngle()));
    }

    public void initialize() {
        Timeline moveTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            updateDirection();
            rotateHead();
            moveHead();
        }));
        moveTimeline.setCycleCount(Animation.INDEFINITE);
        moveTimeline.play();

        player.getTransforms().add(rotation);
        rotation.setPivotY(player.getHeight()/2);

        body.getTransforms().add(bodyRotation);
        bodyRotation.setPivotY(body.getHeight()/2);
        bodyRotation.setPivotX(body.getWidth());

        tail.getTransforms().add(tailRotation);
        tailRotation.setPivotY(tail.getHeight()/2);
        tailRotation.setPivotX(tail.getWidth());

        player.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            //body.setLayoutX(VectorCalc.calcNextX(player.getLayoutX(), rotation.getAngle(), player.getHeight()));
            body.setLayoutX(player.getLayoutX());
        });
        player.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            //body.setLayoutY(VectorCalc.calcNextX(player.getLayoutY(), rotation.getAngle(), player.getWidth()));
            body.setLayoutY(player.getLayoutY());
        });

        player.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            bodyRotation.setAngle(rotation.getAngle() - deltaRotation * 5);
            if (deltaRotation > 0 && bodyDeltaRotation < 3) bodyDeltaRotation += 0.5/5.0;
            if (deltaRotation < 0 && bodyDeltaRotation > -3) bodyDeltaRotation -= 0.5/5.0;
            if (Math.round(deltaRotation) == 0) bodyDeltaRotation += bodyDeltaRotation > 0 ? -bodyDeltaRotation/5.0 : Math.abs(bodyDeltaRotation)/5.0;
        });

        body.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            //body.setLayoutX(VectorCalc.calcNextX(player.getLayoutX(), rotation.getAngle(), player.getHeight()));
            tail.setLayoutX(body.getLayoutX() - body.getWidth() * Math.cos(Math.toRadians(bodyRotation.getAngle())));
        });
        body.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            //body.setLayoutY(VectorCalc.calcNextX(player.getLayoutY(), rotation.getAngle(), player.getWidth()));
            tail.setLayoutY(body.getLayoutY() - body.getWidth() * Math.sin(Math.toRadians(bodyRotation.getAngle())));
        });

        body.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            tailRotation.setAngle(bodyRotation.getAngle() - bodyDeltaRotation * 5);
        });
    }
}