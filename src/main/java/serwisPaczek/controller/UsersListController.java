package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class UsersListController {
    private SceneManager sceneManager;

    @FXML
    public void BackToAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}