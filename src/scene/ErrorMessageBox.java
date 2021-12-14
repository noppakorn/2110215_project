package scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorMessageBox extends Alert {
    public ErrorMessageBox(String s) {
        super(AlertType.ERROR, s, ButtonType.OK);
    }
}
