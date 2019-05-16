package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class UserOrderAddOpinionController {
    private SceneManager sceneManager;

    @FXML
    public void addOpinion(ActionEvent event) {
        sceneManager.show(SceneType.USER_PROFILE_MY_OPINIONS);
    }

    @FXML
    public void openUserMain(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
