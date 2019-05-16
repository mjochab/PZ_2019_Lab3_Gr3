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
    public void openDetails(ActionEvent event) {
        //TODO FIX
        sceneManager.show(SceneType.USER_ORDER_FINALIZE);
    }

    @FXML
    public void addOpinion(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_ADD_OPINION);
    }

    @FXML
    public void openUserMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
