package vinnsla;

/**
 * The class VectorCalc.
 */
public class VectorCalc {
    /**
     * The public method calcMoveX.
     *
     * @param deg receives the angle of movement in degrees.
     * @return returns its responding cosine value.
     */
    public static double calcMoveX(double deg) {
        double rad = Math.toRadians(deg);
        double x = Math.cos(rad);
        return x;
    }

    /**
     * returns here its responding sine value.
     */
    public static double calcMoveY(double deg) {
        double rad = Math.toRadians(deg);
        double y = Math.sin(rad);
        return y;
    }

    /**
     * The method returns the next x position after a movement.
     *
     * @param x     the current x position.
     * @param deg   the degrees of movement.
     * @param width the width of the screen.
     * @return return the next x cordinate as a double.
     */
    public static double calcNextX(double x, double deg, double width) {
        return x - Math.cos(Math.toRadians(deg)) * width;
    }

    /**
     * The method returns the next y position after a movement.
     *
     * @param y      the current y position.
     * @param deg    the degrees of movement.
     * @param height the height of the screen.
     * @return return the next y cordinate as a double.
     */
    public static double calcNextY(double y, double deg, double height) {
        return y - Math.sin(Math.toRadians(deg)) * height;
    }
}
