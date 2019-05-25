package serwisPaczek.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class DialogsUtils {


    /** Method to generate dialog
     * @param text - text what we need to show outside
     */
    public static void showDialog(String text) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
    }
}