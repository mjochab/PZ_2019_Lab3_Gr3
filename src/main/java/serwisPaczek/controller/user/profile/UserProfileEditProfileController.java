package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class UserProfileEditProfileController {
    private SceneManager sceneManager;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void BackToUserProfile(ActionEvent event) {
        sceneManager.show(SceneType.MAIN_USER);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
