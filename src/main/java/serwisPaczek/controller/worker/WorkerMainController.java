package serwisPaczek.controller.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.model.dto.UserLoginDto.setLoggedUser;

@Controller
public class WorkerMainController {
    private SceneManager sceneManager;
    @FXML
    private Button btnWelcomeWorker;

    @FXML
    public void initialize() {
        btnWelcomeWorker.setText("Witaj " + getLoggedUser().getUsername() + "!");
    }

    @FXML
    public void openManageParcelsPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MANAGE_PARCELS);
    }

    @FXML
    void openGiftOrderPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MANAGE_GIFT_ORDERS);
    }

    @FXML
    void openEditGiftPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_EDIT_GIFT);
    }

    @FXML
    void openEditCouponsPanel (ActionEvent event) {
        sceneManager.show(SceneType.WORKER_EDIT_COUPON);
    }

    /**
     * This method is used to logout user
     */
    @FXML
    void logout(ActionEvent event) {
        setLoggedUser(null);
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
