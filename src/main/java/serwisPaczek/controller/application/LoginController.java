package serwisPaczek.controller.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    @FXML
    private TextField TFUsername;
    @FXML
    private PasswordField PFPassword;
    @FXML
    private Text usernameWarning, passwordWarning;

    @FXML
    public void initialize() {
        usernameWarning.setVisible(false);
        passwordWarning.setVisible(false);
    }

    /**
     * This method is used to log in the user.
     */
    @FXML
    public void login(ActionEvent event) throws IOException {
        userService.login(TFUsername.getText(), PFPassword.getText(), usernameWarning, passwordWarning);
    }

    @FXML
    void openRegisterPanel(ActionEvent event) {
        sceneManager.show(SceneType.REGISTER);
    }

    @FXML
    public void openMainAppPanel(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
