package serwisPaczek.controller.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;

@Controller
public class LoginController {
    private SceneManager sceneManager;

    @Autowired
    private UserService userService;
//    @FXML
//    private Text usernameWarning, passwordWarning, repeatPasswordWarning;

    @FXML
    private TextField TFUsername;
    @FXML
    private PasswordField PFPassword;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    void OpenRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {

        userService.login(TFUsername.getText(), PFPassword.getText());
    }


    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
