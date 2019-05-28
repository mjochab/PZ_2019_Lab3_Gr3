package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.About;
import serwisPaczek.repository.AboutRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserAboutController {
    @Autowired
    AboutRepository aboutRepository;
    private SceneManager sceneManager;
    @FXML
    private Label label;

    @FXML
    public void initialize() {
        List<About> listAbout = aboutRepository.findAll();
        label.setText(listAbout.get(0).getContent());
    }

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
