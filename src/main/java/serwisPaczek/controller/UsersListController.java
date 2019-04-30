package serwisPaczek.controller;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.model.User;
import java.util.List;

@Controller
public class UsersListController {

    private SceneManager sceneManager;

//    @Autowired
//    private UserRepository userRepository;
//    List<User> users = userRepository.findAll();
//    ObservableList<User> userObservableList = (ObservableList<User>) users;
//    @FXML private ListView<User> userListView;
//
//    public void initialize(){
//        userListView.setItems(userObservableList);
//    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }



    @FXML
    void DeactivateUser(ActionEvent event) {}

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
