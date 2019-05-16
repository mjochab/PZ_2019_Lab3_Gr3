package serwisPaczek.controller.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class EditGiftController {
    private SceneManager sceneManager;

    @FXML
    public void backToWorkerMain(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
