package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class MainAdminController {
    private SceneManager sceneManager;

    @Autowired
    private MainService mainService;

    @FXML
    public void OpenWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_PANEL);
    }

    @FXML
    public void OpenAddCourier(ActionEvent event) {
        sceneManager.show(SceneType.ADD_COURIER);
    }

    public void FillTheBaseWithExampleData() {
        mainService.fillDatabase();
    }

    @FXML
    public void openAbout(ActionEvent event) {
        sceneManager.show(SceneType.ABOUT);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
