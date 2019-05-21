package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import javafx.scene.control.Label;
import java.awt.*;
import serwisPaczek.model.About;
import java.util.List;
import serwisPaczek.repository.AboutRepository;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserAboutController {
    private SceneManager sceneManager;

    @FXML
    private Label label;

    @Autowired
    AboutRepository aboutRepository;

    @Autowired
    private MainService mainService;

    @FXML
    public void initialize(){
//        if (aboutRepository.findAll().isEmpty()) mainService.addAbout();
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
