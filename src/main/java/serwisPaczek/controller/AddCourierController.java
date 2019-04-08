package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class AddCourierController {
    private SceneManager sceneManager;

    @FXML
    public void OpenAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
