package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class MainUserController {

    private SceneManager sceneManager;

    @FXML
    public void openAbout(ActionEvent event) {
        sceneManager.show(SceneType.ABOUT);
    }

    @FXML
    void OpenLoginPanel(ActionEvent event) {
        sceneManager.show(SceneType.LOGIN);
    }

    @FXML
    void OpenPriceList(ActionEvent event) {
        sceneManager.show(SceneType.PRICE_LIST);
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

    @FXML
    void OpenEditUserProfile(ActionEvent event) {
        sceneManager.show(SceneType.EDIT_USER);
    }

    @FXML
    void logout(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);

    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}