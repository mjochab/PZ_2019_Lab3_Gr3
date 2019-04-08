package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import javafx.fxml.FXML;

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

    @FXML
    void logout(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    public void FillTheBaseWithExampleData(){
        mainService.fillDatabase();
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
