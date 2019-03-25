package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class LoginController {
    private SceneManager sceneManager;

    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    public void OpenRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    public void UserMenu(ActionEvent event){ sceneManager.show(SceneType.USER_MENU);}

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
