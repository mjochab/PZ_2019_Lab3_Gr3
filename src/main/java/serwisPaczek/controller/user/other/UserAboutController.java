package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserAboutController {
    private SceneManager sceneManager;

    @FXML
    public void openMainPanel(ActionEvent event) {
        if (getLoggedUser() == null) {
            sceneManager.show(SceneType.MAIN);
        } else {
            sceneManager.show(SceneType.USER_MAIN);
        }
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
