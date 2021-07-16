public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double dotProduct(Vector v, Vector u) {
        return v.getX() * u.getX() + v.getY() * u.getY();
    }

    public static Vector sum(Vector v, Vector u) {
        return new Vector(v.getX() + u.getX(), v.getY() + u.getY());
    }

    public static Vector sub(Vector v, Vector u) {
        return new Vector(v.getX() - u.getX(), v.getY() - u.getY());
    }

    public static Vector ratio(double k, Vector v) {
        return new Vector(k * v.getX(), k * v.getY());
    }

    public static double cubeLen(Vector v) {
        return dotProduct(v, v);
    }

    public static double len(Vector v) {
        return Math.sqrt(cubeLen(v));
    }

}
