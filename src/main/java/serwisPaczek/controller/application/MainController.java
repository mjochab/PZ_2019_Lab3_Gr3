package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;


@Controller
public class MainController {

    private SceneManager sceneManager;
    @Autowired
    private MainService mainService;

    public MainController() {
    }


    @FXML
    public void openAbout(ActionEvent event) {
        sceneManager.show(SceneType.GUEST_ABOUT);
    }

    @FXML
    void OpenLoginPanel(ActionEvent event) {
        sceneManager.show(SceneType.LOGIN);
    }

    @FXML
    void OpenCourierList(ActionEvent event) {
        sceneManager.show(SceneType.COURIER_LIST);
    }

    @FXML
    void OpenRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    @FXML
    void OpenOrderCourier(ActionEvent event) {
        sceneManager.show(SceneType.ORDER);
    }

    @FXML
    void OpenCourierRanking(ActionEvent event) {
        sceneManager.show(SceneType.COURIER_RANKING);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
