package serwisPaczek.controller.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import java.util.List;
import serwisPaczek.repository.GiftRepository;

@Controller
public class GiftOrderController {
    private SceneManager sceneManager;



    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
