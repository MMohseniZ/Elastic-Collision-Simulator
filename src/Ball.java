import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
    Processor processor = Processor.getInstance();
    private final ArrayList<String> collisions;
    private final String ballName;
    private final Circle circle;
    private final double m;
    private final double radius;
    private Vector f;
    private Vector v;
    private Vector r;
    private double vx;
    private double vy;
    private double rx;
    private double ry;
    private final int width;
    private final int height;

    public Ball(String ballName, double m, double radius, Vector v, Vector r, int width, int height) {
        this.ballName = ballName;
        this.m = m;
        this.radius = radius;
        this.v = v;
        this.r = r;
        vx = v.getX();
        vy = v.getY();
        rx = r.getX();
        ry = r.getY();
        this.width = width;
        this.height = height;
        this.circle = new Circle(this.radius, Color.rgb(new Random().nextInt(200), new Random().nextInt(200), new Random().nextInt(200)));
        f = new Vector(0, 0);
        collisions = new ArrayList<>();
        getCirclePos();
    }

    public Ball(String ballName, double m, double radius, Vector r, int width, int height) {
        this.ballName = ballName;
        this.m = m;
        this.radius = radius;
        this.r = r;
        this.width = width;
        this.height = height;
        this.v = new Vector(0, 0);
        this.circle = new Circle(this.radius, Color.rgb(new Random().nextInt(200), new Random().nextInt(200), new Random().nextInt(200)));
        f = new Vector(0, 0);
        collisions = new ArrayList<>();
        getCirclePos();
    }

    public void move(double dt, double t) {
        accelerate(dt);
        r = Vector.sum(r, Vector.ratio(dt, v));

        if (r.getX() + radius > width) {
            v.setX(-v.getX());
            r.setX(width - radius);
            collisions.add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
            processor.getAllCollisions().add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
        } else if (r.getX() - radius < 0) {
            v.setX(-v.getX());
            r.setX(radius);
            collisions.add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
            processor.getAllCollisions().add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
        }

        if (r.getY() + radius > height) {
            v.setY(-v.getY());
            r.setY(height - radius);
            collisions.add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
            processor.getAllCollisions().add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
        } else if (r.getY() - radius < 0) {
            v.setY(-v.getY());
            r.setY(radius);
            collisions.add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
            processor.getAllCollisions().add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), Wall", t, ballName, r.getX(), r.getY()));
        }

        getCirclePos();
    }

    public void accelerate(double dt) {
        setV(Vector.sum(getV(), Vector.ratio(dt / m, f)));
    }

    public static boolean isCollided(Ball b1, Ball b2) {
        return Math.sqrt(Math.pow(b1.getCircle().getCenterX() - b2.getCircle().getCenterX(), 2) + Math.pow(b1.getCircle().getCenterY() - b2.getCircle().getCenterY(), 2)) <= b1.getCircle().getRadius() + b2.getCircle().getRadius();
    }

    public static void collision(Ball b1, Ball b2) {
        Vector r1 = b1.getR();
        Vector r2 = b2.getR();
        Vector v1 = b1.getV();
        Vector v2 = b2.getV();
        double m1 = b1.getM();
        double m2 = b2.getM();
        Vector newV1 = Vector.sub(v1, Vector.ratio(2 * m2 * Vector.dotProduct(Vector.sub(v1, v2), Vector.sub(r1, r2)) / (Vector.cubeLen(Vector.sub(r1, r2)) * (m1 + m2)), Vector.sub(r1, r2)));
        Vector newV2 = Vector.sub(v2, Vector.ratio(2 * m1 * Vector.dotProduct(Vector.sub(v2, v1), Vector.sub(r2, r1)) / (Vector.cubeLen(Vector.sub(r2, r1)) * (m2 + m1)), Vector.sub(r2, r1)));
        b1.setV(newV1);
        b2.setV(newV2);
        double len = Vector.len(Vector.sub(r2, r1));
        b1.setR(Vector.sum(Vector.ratio((b1.radius + b2.radius - len) / len, Vector.sub(r1, r2)), r1));
        b2.setR(Vector.sum(Vector.ratio((b2.radius + b1.radius - len) / len, Vector.sub(r2, r1)), r2));
    }

    public void setV(Vector v) {
        this.v = v;
    }

    public void setF(Vector windV) {
        f = Vector.ratio(-radius, Vector.sub(v, windV));
    }

    public void setR(Vector r) {
        this.r = r;
    }

    private void getCirclePos() {
        this.circle.setCenterY(height - this.r.getY());
        this.circle.setCenterX(this.r.getX());
    }

    public Circle getCircle() {
        return circle;
    }

    public double getM() {
        return m;
    }

    public Vector getV() {
        return new Vector(v.getX(), v.getY());
    }

    public String getBallName() {
        return ballName;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getR() {
        return new Vector(r.getX(), r.getY());
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public Vector getF() {
        return f;
    }

    public double getRx() {
        rx = r.getX();
        return rx;
    }

    public double getRy() {
        ry = r.getY();
        return ry;
    }

    public ArrayList<String> getCollisions() {
        return collisions;
    }
}
