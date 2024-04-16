package org.example.worm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import vinnsla.VectorCalc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The class Worm that represents the worm in the gameplay.
 * It contains all the necessary methods to move the worm and to elongate it.
 * It also contains methods to check for collisions with the leikbord and to update the segments of the worm.
 */
public class Worm extends AnchorPane {

    @FXML
    private Leikbord leikbord;
    @FXML
    private Worm worm;
    @FXML
    private ImageView player;
    private Rotate rotation = new Rotate();
    private double deltaRotation;
    @FXML
    private ImageView body;
    private Rotate bodyRotation = new Rotate();
    private double bodyDeltaRotation;
    private ArrayList<Body> bodySections = new ArrayList<>();

    @FXML
    private ImageView tail;
    private Rotate tailRotation = new Rotate();

    private boolean left;
    private boolean right;

    private boolean up = false; // Óli did this

    /**
     * The number of segments of the worm.
     */
    public static int numberSegments;
    private int speed = 5;

    private Timeline moveTimeline;

    private enum Direction {
        /**
         * Left direction.
         */
        LEFT,
        /**
         * Right direction.
         */
        RIGHT,
        /**
         * Forward direction.
         */
        FORWARD,
        /**
         * Same direction as last frame.
         */
        STILL;
    }

    private Direction direction;

    /**
     * The constructor of the Worm class.
     * It initializes the FXML file and sets the necessary variables.
     * It also implements the listeners for the movement of the worm.
     * @throws IOException thorws an exception if the FXML file cannot be loaded.
     */
    public Worm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/worm/worm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The setLeikbord method sets the leikbord of the worm.
     *
     * @param leikbord specifies the leikbord of the worm.
     */
    public void setLeikbord(Leikbord leikbord) {
        this.leikbord = leikbord;
    }

    /**
     * The movement handler for pressing A or D.
     *
     * @param keyEvent an event that represents a key press.
     */
    @FXML
    protected void movementPressHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                left=true;
                break;
            case D:
                right=true;
                break;
            default:
                break;
        }

    }

    /**
     * The movement handler for releasing A or D.
     *
     * @param keyEvent an event that represents a key release.
     */
    @FXML
    protected void movementReleaseHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                left=false;
                break;
            case D:
                right=false;
                break;
            default:
                break;
        }

    }


    /**
     * The updateDirection method updates the direction of the worm based on the left and right movement keys.
     *
     * @param keyEvent the event that is triggered when a key is pressed or released.
     */
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

    /**
     * The rotateHead method rotates the head of the worm based on the direction of movement.
     * It updates the deltaRotation variable based on the direction of movement.
     */
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


    /**
     * The rotateBody method rotates the body of the worm based on the direction of movement.
     * It updates the bodyDeltaRotation variable based on the direction of movement.
     */
    private void moveHead() {
        player.setLayoutX(player.getLayoutX() + speed*VectorCalc.calcMoveX(rotation.getAngle()));
        player.setLayoutY(player.getLayoutY() + speed*VectorCalc.calcMoveY(rotation.getAngle()));
    }

    /**
     * The start method starts the animation of the worm.
     */
    public void start() {
        if (moveTimeline != null) {
            moveTimeline.stop();
        }
        this.setOpacity(1.0);
        player.setLayoutX(-100);
        player.setLayoutY(300);
        rotation.setAngle(0);
        moveTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            updateDirection();
            rotateHead();
            moveHead();
        }));
        moveTimeline.setCycleCount(Animation.INDEFINITE);
        moveTimeline.play();
    }

    /**
     * The enlarge method adds a new segment to the worm.
     */
    public void enlarge() {
        Body body1 = new Body(last());
        bodySections.add(body1);
        this.getChildren().add(body1);
        updateSegments();
    }

    /**
     * The method getHeadPos returns the position of the head of the worm.
     *
     * @return return the head position of the worm in the form of an array of doubles.
     */
    public double[] getHeadPos() {
        return new double[]{player.getLayoutX() + player.getFitWidth()/2 * Math.cos(Math.toRadians(rotation.getAngle())), player.getLayoutY() + player.getFitWidth()/2 * Math.sin(Math.toRadians(rotation.getAngle()))};
    }

    private Body last() {
        return bodySections.get(bodySections.size()-1);
    }

    /**
     * The updateSegments method updates the number of segments of the worm.
     */
    private void updateSegments() {
        numberSegments = bodySections.size()+2;
    }

    /**
     * The initialize method initializes the worm.
     * It adds the body segments to the worm and sets the necessary listeners.
     */
    public void initialize() {

        Body body1 = new Body(body, bodyRotation);
        bodySections.add(body1);
        this.getChildren().add(body1);

        updateSegments();

        player.getTransforms().add(rotation);
        rotation.setPivotY(player.getFitHeight()/2);

        body.getTransforms().add(bodyRotation);
        bodyRotation.setPivotY(body.getFitHeight()/2);
        bodyRotation.setPivotX(body.getFitWidth());

        tail.getTransforms().add(tailRotation);
        tailRotation.setPivotY(tail.getFitHeight()/2);
        tailRotation.setPivotX(tail.getFitWidth());
        player.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    leikbord.checkCollision(getHeadPos());
                } catch (NullPointerException ignored) {}
        });

        player.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            body.setLayoutX(player.getLayoutX() + 10 * Math.cos(Math.toRadians(rotation.getAngle())));
            //body.setLayoutX(VectorCalc.calcNextX(player.getLayoutX(), rotation.getAngle(), body.getFitWidth()));

        });
        player.layoutYProperty().addListener((observable, oldValue, newValue) -> {
            body.setLayoutY(player.getLayoutY() + 10 * Math.sin(Math.toRadians(rotation.getAngle())));
            //body.setLayoutY(VectorCalc.calcNextY(player.getLayoutY(), rotation.getAngle(), body.getFitWidth()));
        });

        player.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            //bodyRotation.setAngle(rotation.getAngle() - deltaRotation * 5);
            //if (deltaRotation > 0 && bodyDeltaRotation < 3) bodyDeltaRotation += 0.5/5.0;
            //if (deltaRotation < 0 && bodyDeltaRotation > -3) bodyDeltaRotation -= 0.5/5.0;
            //if (Math.round(deltaRotation) == 0) bodyDeltaRotation += bodyDeltaRotation > 0 ? -bodyDeltaRotation/5.0 : Math.abs(bodyDeltaRotation)/5.0;
            bodyRotation.setAngle(bodyRotation.getAngle() + (rotation.getAngle()-bodyRotation.getAngle())/40);
        });

        last().layoutXProperty().addListener((observable, oldValue, newValue) -> {
            tail.setLayoutX(last().getLayoutX() - (last().getFitWidth() - 10) * Math.cos(Math.toRadians(last().rotation.getAngle())));
        });
        last().layoutYProperty().addListener((observable, oldValue, newValue) -> {
            tail.setLayoutY(last().getLayoutY() - (last().getFitWidth() - 10) * Math.sin(Math.toRadians(last().rotation.getAngle())));
        });

        last().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            tailRotation.setAngle(tailRotation.getAngle() + (last().rotation.getAngle() - tailRotation.getAngle())/(40+numberSegments*5));
        });
    }
}
