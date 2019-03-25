package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class MainAdminController {
    private SceneManager sceneManager;

    public void OpenWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_PANEL);
    }

    public void OpenAddCourier(ActionEvent event) { sceneManager.show(SceneType.ADD_COURIER); }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
