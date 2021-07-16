import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class WindSetting {
    Processor processor = Processor.getInstance();

    @FXML
    private TextField a1;

    @FXML
    private TextField a2;

    @FXML
    private TextField a3;

    @FXML
    private TextField a4;

    @FXML
    private TextField b1;

    @FXML
    private TextField b2;

    @FXML
    private TextField b3;

    @FXML
    private TextField b4;

    @FXML
    private TextField b5;

    @FXML
    private TextField b6;

    @FXML
    private TextField c1;

    @FXML
    private TextField c2;

    @FXML
    private TextField c3;

    @FXML
    private TextField c4;

    @FXML
    private TextField c5;

    @FXML
    private TextField c6;

    @FXML
    private Label label;

    @FXML
    void addVxa() {
        if (a1.getText().isEmpty() || a2.getText().isEmpty() || a3.getText().isEmpty() || a4.getText().isEmpty())
            label.setText("enter all values");
        else {
            try {
                processor.getWind().getVx().add(new Function(Double.parseDouble(a1.getText()), Double.parseDouble(a2.getText()), Double.parseDouble(a3.getText()), Double.parseDouble(a4.getText()), 1));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }

    }

    @FXML
    void addVxb() {
        if (b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty() || b5.getText().isEmpty() || b6.getText().isEmpty())
            label.setText("enter all values");
        else {
            try {
                processor.getWind().getVx().add(new Function(Double.parseDouble(b1.getText()), Double.parseDouble(b2.getText()), Double.parseDouble(b3.getText()), Double.parseDouble(b4.getText()), Double.parseDouble(b5.getText()), Double.parseDouble(b6.getText()), 2));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }
    }

    @FXML
    void addVxc() {
        if (c1.getText().isEmpty() || c2.getText().isEmpty() || c3.getText().isEmpty() || c4.getText().isEmpty() || c5.getText().isEmpty() || c6.getText().isEmpty())
            label.setText("enter all values");
        else {
            try {
                processor.getWind().getVx().add(new Function(Double.parseDouble(c1.getText()), Double.parseDouble(c2.getText()), Double.parseDouble(c3.getText()), Double.parseDouble(c4.getText()), Double.parseDouble(c5.getText()), Double.parseDouble(c6.getText()), 3));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }
    }

    @FXML
    void addVya() {
        if (a1.getText().isEmpty() || a2.getText().isEmpty() || a3.getText().isEmpty() || a4.getText().isEmpty())
            label.setText("complete all values");
        else {
            try {
                processor.getWind().getVy().add(new Function(Double.parseDouble(a1.getText()), Double.parseDouble(a2.getText()), Double.parseDouble(a3.getText()), Double.parseDouble(a4.getText()), 1));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }
    }

    @FXML
    void addVyb() {
        if (b1.getText().isEmpty() || b2.getText().isEmpty() || b3.getText().isEmpty() || b4.getText().isEmpty() || b5.getText().isEmpty() || b6.getText().isEmpty())
            label.setText("enter all values");
        else {
            try {
                processor.getWind().getVy().add(new Function(Double.parseDouble(b1.getText()), Double.parseDouble(b2.getText()), Double.parseDouble(b3.getText()), Double.parseDouble(b4.getText()), Double.parseDouble(b5.getText()), Double.parseDouble(b6.getText()), 2));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }
    }

    @FXML
    void addVyc() {
        if (c1.getText().isEmpty() || c2.getText().isEmpty() || c3.getText().isEmpty() || c4.getText().isEmpty() || c5.getText().isEmpty() || c6.getText().isEmpty())
            label.setText("enter all values");
        else {
            try {
                processor.getWind().getVy().add(new Function(Double.parseDouble(c1.getText()), Double.parseDouble(c2.getText()), Double.parseDouble(c3.getText()), Double.parseDouble(c4.getText()), Double.parseDouble(c5.getText()), Double.parseDouble(c6.getText()), 3));
            } catch (NumberFormatException e) {
                label.setText("invalid inputs");
            }
        }
    }

    @FXML
    void back() throws IOException {
        new Main().changeScene("initPage.fxml");
    }

    @FXML
    void resetVx() {
        processor.getWind().setVxArr(new ArrayList<>());
        label.setText("Winds Vx = 0");
    }

    @FXML
    void resetVy() {
        processor.getWind().setVyArr(new ArrayList<>());
        label.setText("Winds Vy = 0");
    }

}
