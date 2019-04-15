package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.User;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

@Controller
public class UsersListController {
    private SceneManager sceneManager;

    @Autowired
    UserRepository userRepository;

    public void fillTest(ActionEvent event) {
        System.out.println("fillTest");
        List<User> listUsers = userRepository.findAll();
    }

    @FXML
    public void BackToAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}