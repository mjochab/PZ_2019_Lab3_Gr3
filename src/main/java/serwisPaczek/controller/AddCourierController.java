package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

public class AddCourierController {

    private SceneManager sceneManager;

    @FXML
    public void OpenAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }
}
