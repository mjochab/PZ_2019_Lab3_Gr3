package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import javafx.scene.control.Label;
import java.awt.*;
import serwisPaczek.model.About;
import java.util.List;
import serwisPaczek.repository.AboutRepository;

@Controller
public class UserAboutController {
    private SceneManager sceneManager;

    @FXML
    private Label label;

    @Autowired
    AboutRepository aboutRepository;

    @FXML
    public void initialize(){
        List<About> listAbout = aboutRepository.findAll();
        label.setText(listAbout.get(0).getContent());
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        //TODO[ALAN]: MAIN or USER_MAIN
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
