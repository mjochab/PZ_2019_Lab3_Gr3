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

    public void FillTheBaseWithExampleData() {
        mainService.fillDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Dane zostały poprawnie dodane do bazy", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
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
