package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

public class UserProfileController {
    private SceneManager sceneManager;

    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }


    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
