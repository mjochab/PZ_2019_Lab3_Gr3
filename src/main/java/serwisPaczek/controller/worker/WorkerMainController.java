package serwisPaczek.controller.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class MainWorkerController {
    private SceneManager sceneManager;

    @FXML
    public void OpenWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_PANEL);
    }

    @FXML
    void logout(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    void OpenGiftOrderPanel(ActionEvent event) {sceneManager.show(SceneType.GIFT_ORDER);}

    @FXML
    void OpenEditGiftPanel(ActionEvent event) {sceneManager.show(SceneType.EDIT_GIFT);}


    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
