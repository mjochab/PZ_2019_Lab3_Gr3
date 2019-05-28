package serwisPaczek.controller.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void openAboutPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_ABOUT);
    }

    @FXML
    void openLoginPanel(ActionEvent event) {
        sceneManager.show(SceneType.LOGIN);
    }

    @FXML
    void openCourierCompaniesListPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_COMPANIES_LIST);
    }

    @FXML
    void openRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    @FXML
    void openOrderCourierMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_MAIN);
    }

    @FXML
    void openCourierRankingPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_RANKING);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
