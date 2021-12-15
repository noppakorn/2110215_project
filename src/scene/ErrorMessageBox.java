package scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The type Error message box which will be shown when the user input invalid data.
 */
public class ErrorMessageBox extends Alert {
    /**
     * Instantiates a new Error message box.
     *
     * @param s the s
     */
    public ErrorMessageBox(String s) {
        super(AlertType.ERROR, s, ButtonType.OK);
    }
}
