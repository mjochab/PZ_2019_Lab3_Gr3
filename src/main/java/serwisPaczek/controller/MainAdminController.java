package serwisPaczek.controller;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import javafx.fxml.FXML;

@Controller
public class MainAdminController {
    private SceneManager sceneManager;
    //    @Autowired
    //    private RoleRepository roleRepository;
    //    @Autowired
    //    private UserRepository userRepository;

    @FXML
    public void OpenWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_PANEL);
    }

    @FXML
    public void OpenAddCourier(ActionEvent event) {
        sceneManager.show(SceneType.ADD_COURIER);
    }

    @FXML
    void logout(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    //    public void FillTheBaseWithExampleData(){
    //        Role role = new Role(1L,"ROLE_ADMIN");
    //        User user = new User(1L,"Patryk","Brzuchacz",role);
    //        roleRepository.save(role);
    //        userRepository.save(user);
    //    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
