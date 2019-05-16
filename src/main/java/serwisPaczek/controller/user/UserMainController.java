package serwisPaczek.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserMainController {

    private SceneManager sceneManager;
    @FXML
    private Button btnWelcomeUser;

    @FXML
    public void initialize() {
        btnWelcomeUser.setText("Witaj " + getLoggedUser().getUsername() + "!");
    }

    @FXML
    public void openAbout(ActionEvent event) {
        sceneManager.show(SceneType.USER_ABOUT);
    }

    @FXML
    void OpenPriceList(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_COMPANIES_LIST);
    }

    @FXML
    void OpenOrderCourier(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_MAIN);
    }

    @FXML
    void OpenCourierRanking(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_RANKING);
    }

    @FXML
    void OpenEditUserProfile(ActionEvent event) {
        sceneManager.show(SceneType.USER_PROFILE_EDIT_PROFILE);
    }

    @FXML
    void openEditOrderList(ActionEvent event) {
        sceneManager.show(SceneType.USER_PROFILE_ORDER_LIST);
    }

    @FXML
    void openUserOpinions(ActionEvent event) {
        sceneManager.show(SceneType.USER_PROFILE_MY_OPINIONS);
    }

    @FXML
    void openUserWallet(ActionEvent event) {
        //TODO create user wallet .fxml
    }

    @FXML
    void OpenOrderGiftPanel(ActionEvent event) {
        // TODO FIX
        sceneManager.show(SceneType.USER_GIFT_ORDER);
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