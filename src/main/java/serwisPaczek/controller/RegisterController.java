package serwisPaczek.controller;

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

@Controller
public class RegisterController {

    private SceneManager sceneManager;

    @Autowired
    private UserService userService;

    @FXML
    private Text usernameWarning, passwordWarning, repeatPasswordWarning;

    @FXML
    private PasswordField PFPassword;

    @FXML
    private PasswordField PFRepeatPassword;

    @FXML
    private TextField TFUsername;

    @FXML
    public void initialize() {
   usernameWarning.setVisible(false);
   passwordWarning.setVisible(false);
   repeatPasswordWarning.setVisible(false);
    }

    @FXML
    public void register(ActionEvent event) {
        userService.createUser(TFUsername.getText(),PFPassword.getText(),
                PFRepeatPassword.getText(),
                usernameWarning,
                passwordWarning,
                repeatPasswordWarning);;
    }

    @FXML
    public void openLoginPanel(ActionEvent event) {
        sceneManager.show(SceneType.LOGIN);
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
