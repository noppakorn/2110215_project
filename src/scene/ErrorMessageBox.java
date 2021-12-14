package scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The type Error message box.
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
