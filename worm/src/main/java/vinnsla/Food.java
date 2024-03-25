package vinnsla;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food {
    private Rectangle rectangle;
    private int maxX;
    private int maxY;

    public Food(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.rectangle = new Rectangle(10, 10, Color.RED);
        relocate();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void relocate() {
        Random random = new Random();
        rectangle.setX(random.nextInt(maxX));
        rectangle.setY(random.nextInt(maxY));
    }
}

