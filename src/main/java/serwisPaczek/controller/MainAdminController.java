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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Dane zostały poprawnie dodane do bazy", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    public void OpenEditCourier(ActionEvent event) {sceneManager.show(SceneType.EDIT_COURIER);}

    @FXML
    public void openAbout(ActionEvent event) {
        sceneManager.show(SceneType.ABOUT);
    }

    @FXML
    public void openUsersList(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_USERS_LIST);
    }

    @FXML
    void logout(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }
    @FXML
    void EditAbout(ActionEvent event) {sceneManager.show(SceneType.EDIT_ABOUT);}
    @FXML
    void OpenUserList(ActionEvent event) {sceneManager.show(SceneType.USERS_LIST);}

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}