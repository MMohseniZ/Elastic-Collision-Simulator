import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Processor {
    private final Wind wind;
    private boolean isThereAir;
    private boolean isSimulating;
    private Timeline timeline;
    private Scene scene;
    public static Processor processor = null;
    private ArrayList<String> allCollisions;
    private final ArrayList<Ball> ball;
    private ArrayList<Ball> tmpBalls;
    private int width, height;
    private double simTime;
    private double dt;
    private int rounds;

    public Processor() {
        wind = new Wind();
        ball = new ArrayList<>();
        allCollisions = new ArrayList<>();
        rounds = 0;
    }

    public static Processor getInstance() {
        if (processor == null)
            processor = new Processor();
        return processor;
    }

    public void simulate() throws IOException {
        Button endButton = new Button("END");
        Button collButton = new Button("Collisions");
        Label label = new Label();
        label.setLayoutX(5);
        label.setLayoutY(5);
        label.setTextFill(Color.BLACK);
        label.setMaxWidth(300);
        endButton.setLayoutX(5);
        endButton.setLayoutY(height - 30);
        endButton.setOnAction(e -> {
            try {
                new Main().changeScene("initPage.fxml");
                allCollisions = new ArrayList<>();
                timeline.stop();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        collButton.setLayoutX(width - 70);
        collButton.setLayoutY(height - 30);
        collButton.setOnAction(e -> {
            try {
                new Main().changeScene("collisionPage.fxml");
                timeline.pause();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Pane pane = new Pane();
        timeline = new Timeline(new KeyFrame(Duration.millis(dt * 1000), e -> run(label)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        tmpBalls = new ArrayList<>();
        for (Ball tmpBall : ball) {
            tmpBalls.add(new Ball(tmpBall.getBallName(), tmpBall.getM(), tmpBall.getRadius(), tmpBall.getV(), tmpBall.getR(), width, height));
        }
        for (Ball tmpBall : tmpBalls) {
            pane.getChildren().add(tmpBall.getCircle());
        }
        pane.getChildren().add(endButton);
        pane.getChildren().add(collButton);
        pane.getChildren().add(label);
        scene = new Scene(pane, width, height);
        new Main().changeScene(scene);
        timeline.play();
    }

    private void run(Label label) {
        isSimulating = true;
        label.setText(String.format("%.4f", dt * rounds) + " sec / " + String.format("%.4f", simTime) + " sec");
        rounds++;
        double t = rounds * dt;
        for (int i = 0; i < tmpBalls.size(); i++) {
            for (int j = i + 1; j < tmpBalls.size(); j++)
                if (Ball.isCollided(tmpBalls.get(i), tmpBalls.get(j))) {
                    allCollisions.add(String.format("t = %.4fs: %s(x, y) = (%.4f, %.4f), %s(x, y) = (%.4f, %.4f)", t, tmpBalls.get(i).getBallName(), tmpBalls.get(i).getRx(), tmpBalls.get(i).getRy(), tmpBalls.get(j).getBallName(), tmpBalls.get(j).getRx(), tmpBalls.get(j).getRy()));
                    tmpBalls.get(i).getCollisions().add(String.format("t = %.5fs: %s, (x, y) = (%.4f, %.4f)", t, tmpBalls.get(j).getBallName(), tmpBalls.get(j).getRx(), tmpBalls.get(j).getRy()));
                    tmpBalls.get(j).getCollisions().add(String.format("t = %.5fs: %s, (x, y) = (%.4f, %.4f)", t, tmpBalls.get(i).getBallName(), tmpBalls.get(i).getRx(), tmpBalls.get(i).getRy()));
                    Ball.collision(tmpBalls.get(i), tmpBalls.get(j));
                }
        }
        for (Ball tmpBall : tmpBalls) {
            if (isThereAir)
                tmpBall.setF(wind.getWindV(tmpBall.getRx(), tmpBall.getRy(), t));
            tmpBall.move(dt, t);
        }
        if (t >= simTime) {
            rounds = 0;
            label.setText("Simulated successfully");
            isSimulating = false;
            timeline.stop();
        }
    }

    public void setSimTime(double simTime) {
        this.simTime = simTime;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setThereAir(boolean thereAir) {
        isThereAir = thereAir;
    }

    public ArrayList<Ball> getBall() {
        return ball;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Wind getWind() {
        return wind;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<String> getAllCollisions() {
        return allCollisions;
    }

    public ArrayList<Ball> getTmpBalls() {
        return tmpBalls;
    }

    public boolean isSimulating() {
        return isSimulating;
    }

    public boolean isThereAir() {
        return isThereAir;
    }
}
