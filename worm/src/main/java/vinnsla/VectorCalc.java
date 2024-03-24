package vinnsla;

public class VectorCalc {
    public static double calcMoveX(double deg) {
        double rad = Math.toRadians(deg);
        double x = Math.cos(rad);
        return x;
    }

    public static double calcMoveY(double deg) {
        double rad = Math.toRadians(deg);
        double y = Math.sin(rad);
        return y;
    }

    public static double calcNextX(double x, double deg, double height) {
        return x + Math.cos(Math.toRadians(deg)) * height;
    }

    public static double calcNextY(double y, double deg, double width) {
        return y + Math.sin(Math.toRadians(deg)) * width;
    }
}
