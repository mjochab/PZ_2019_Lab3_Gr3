package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class OrderController {
    private SceneManager sceneManager;

    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    public void OpenPriceListButton(ActionEvent event) {
        sceneManager.show(SceneType.CHOOSE_COURIER);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
