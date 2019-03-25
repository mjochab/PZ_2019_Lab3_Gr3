package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class FillAdressessController {
    private SceneManager sceneManager;

    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    public void OpenFinalize(ActionEvent event) {
        sceneManager.show(SceneType.FINALIZE);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}