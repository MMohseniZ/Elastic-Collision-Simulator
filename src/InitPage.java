import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitPage implements Initializable {

    Processor processor = Processor.getInstance();
    ObservableList<Ball> listToShow = FXCollections.observableArrayList(processor.getBall());

    @FXML
    private TableView<Ball> table;

    @FXML
    private TableColumn<Ball, String> tableName;

    @FXML
    private TableColumn<Ball, Double> tableRadius;

    @FXML
    private TableColumn<Ball, Double> tableMass;

    @FXML
    private TableColumn<Ball, Double> tableVx;

    @FXML
    private TableColumn<Ball, Double> tableVy;

    @FXML
    private TableColumn<Ball, Double> tableRx;

    @FXML
    private TableColumn<Ball, Double> tableRy;

    @FXML
    private TextField addName;

    @FXML
    private TextField addRadius;

    @FXML
    private TextField addMass;

    @FXML
    private TextField addVx;

    @FXML
    private TextField addVy;

    @FXML
    private TextField addRx;

    @FXML
    private TextField addRy;

    @FXML
    private TextField simTime;

    @FXML
    private TextField dt;

    @FXML
    private TextField removeName;

    @FXML
    private Label label;

    @FXML
    private TextField width;

    @FXML
    private TextField height;

    @FXML
    private CheckBox hasAir;

    @FXML
    private Button windSettingButton;

    @FXML
    void addBall() {
        boolean isThereWithSameName = false;
        boolean isItHasName = !addName.getText().isEmpty();
        if (isItHasName) {
            for (Ball ball : processor.getBall())
                if (ball.getBallName().equals(addName.getText())) {
                    isThereWithSameName = true;
                    break;
                }
            if (!isThereWithSameName) {
                try {
                    boolean isColliding = false;
                    Ball b = new Ball("", 1, Double.parseDouble(addRadius.getText()), new Vector(0, 0), 900, 900);
                    for (Ball ball : processor.getBall()) {
                        if (Ball.isCollided(ball, b)) {
                            isColliding = true;
                            label.setText("this ball has interference with other balls");
                            break;
                        }
                    }
                    if (!isColliding) {
                        if (addVx.getText().isEmpty() && addVy.getText().isEmpty())
                            processor.getBall().add(new Ball(addName.getText(), Double.parseDouble(addMass.getText()), Double.parseDouble(addRadius.getText()), new Vector(Double.parseDouble(addRx.getText()), Double.parseDouble(addRy.getText())), processor.getWidth(), processor.getHeight()));
                        else if (!addVx.getText().isEmpty() && !addVy.getText().isEmpty())
                            processor.getBall().add(new Ball(addName.getText(), Double.parseDouble(addMass.getText()), Double.parseDouble(addRadius.getText()), new Vector(Double.parseDouble(addVx.getText()), Double.parseDouble(addVy.getText())), new Vector(Double.parseDouble(addRx.getText()), Double.parseDouble(addRy.getText())), processor.getWidth(), processor.getHeight()));
                        new Main().changeScene("initPage.fxml");
                    }
                } catch (NumberFormatException | IOException e) {
                    label.setText("invalid inputs!");
                }
            } else
                label.setText("there's already a ball with this name");
        } else
            label.setText("you have to choose a name");
    }

    @FXML
    void exit() {
        new Main().close();
    }

    @FXML
    void goToSimulationPage() throws IOException {
        if (simTime.getText().isEmpty() || dt.getText().isEmpty() || height.getText().isEmpty() || width.getText().isEmpty())
            label.setText("simulation Time, dt, width and height have not set yet");
        else {
            try {
                processor.setSimTime(Double.parseDouble(simTime.getText()));
                processor.setDt(Double.parseDouble(dt.getText()));
                processor.setWidth(Integer.parseInt(width.getText()));
                processor.setHeight(Integer.parseInt(height.getText()));
                processor.simulate();
            } catch (NumberFormatException e) {
                label.setText("invalid data types!");
            }
        }
    }

    @FXML
    void goToWindSettingPage() throws IOException {
        new Main().changeScene("windSetting.fxml");
    }

    @FXML
    void removeBall() throws IOException {
        boolean isFound = false;
        if (removeName.getText().isEmpty())
            label.setText("you have to enter name");
        else {
            Ball tmpBall = null;
            for (Ball ball : processor.getBall()) {
                if (ball.getBallName().equals(removeName.getText())) {
                    tmpBall = ball;
                    isFound = true;
                }
            }
            if (isFound) {
                processor.getBall().remove(tmpBall);
                new Main().changeScene("initPage.fxml");
            } else
                label.setText("there is no ball with this name");
        }
    }

    @FXML
    void airOptionSet() {
        processor.setThereAir(!processor.isThereAir());
        windSettingButton.setDisable(!windSettingButton.isDisable());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableName.setCellValueFactory(new PropertyValueFactory<>("ballName"));
        tableRadius.setCellValueFactory(new PropertyValueFactory<>("radius"));
        tableMass.setCellValueFactory(new PropertyValueFactory<>("m"));
        tableVx.setCellValueFactory(new PropertyValueFactory<>("vx"));
        tableVy.setCellValueFactory(new PropertyValueFactory<>("vy"));
        tableRx.setCellValueFactory(new PropertyValueFactory<>("rx"));
        tableRy.setCellValueFactory(new PropertyValueFactory<>("ry"));
        windSettingButton.setDisable(true);
        table.setItems(listToShow);
    }
}
