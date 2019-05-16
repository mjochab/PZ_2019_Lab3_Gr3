package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class UserProfileOrderListController {
    private SceneManager sceneManager;

    @FXML
    public void openUserMain(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @FXML
    public void OpenFinalize(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_FINALIZE);
    }

    @FXML
    public void AddOpinion(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_ADD_OPINION);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
