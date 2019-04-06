package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Role;
import serwisPaczek.model.User;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    private SceneManager sceneManager;
    @Autowired
    private MainService mainService;

    public MainController() {
    }

    public void FillTheBaseWithExampleData(){
        mainService.fillDatabase();
    }

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

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
