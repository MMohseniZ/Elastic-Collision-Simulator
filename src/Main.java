import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        Processor processor = Processor.getInstance();
        launch(args);
    }


    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("initPage.fxml")));
        stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Collision Simulator");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage.setScene(new Scene(newRoot));
    }
    public void changeScene(Scene scene) throws IOException {
        stage.setScene(scene);
    }
    public void close(){
        stage.close();
    }
}
