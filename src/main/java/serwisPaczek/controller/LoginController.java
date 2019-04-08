package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class LoginController {
    private SceneManager sceneManager;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    void OpenRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    @FXML
    public void UserMenu(ActionEvent event) {
        sceneManager.show(SceneType.USER_MENU);
    }

    @FXML
    public void OpenAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @FXML
    public void OpenWorker(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MENU);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
