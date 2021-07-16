import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CollisionPage {
    Processor processor = Processor.getInstance();
    @FXML
    private TextArea textArea;

    @FXML
    private TextField ballName;

    @FXML
    private Label label;

    @FXML
    void back() throws IOException {
        if(processor.isSimulating())
            processor.getTimeline().play();
        new Main().changeScene(processor.getScene());
    }

    @FXML
    void printAllCollisions() {
        String text = "";
        for (String allCollision : processor.getAllCollisions()) {
            text += allCollision + "\n";
        }
        textArea.setText(text);
    }

    @FXML
    void printCollisions() {
        String text = "";
        boolean isFound = false;
        if (ballName.getText().isEmpty())
            label.setText("enter name");
        else {
            for (Ball tmpBall : processor.getTmpBalls()) {
                if(tmpBall.getBallName().equals(ballName.getText())){
                    isFound = true;
                    for (String collision : tmpBall.getCollisions()) {
                        text += collision + "\n";
                    }
                    break;
                }
            }
            if(isFound)
                textArea.setText(text);
            else
                label.setText("there is no ball with this name");
        }
    }

}
