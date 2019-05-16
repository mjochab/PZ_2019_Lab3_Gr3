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
    public void openAboutPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_ABOUT);
    }

    @FXML
    void openCourierCompaniesListPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_COMPANIES_LIST);
    }

    @FXML
    void openOrderCourierMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_MAIN);
    }

    @FXML
    void openCourierRankingPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_RANKING);
    }

    @FXML
    void openEditUserProfilePanel(ActionEvent event) {
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
    void openOrderGiftPanel(ActionEvent event) {
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