package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Opinion;
import serwisPaczek.repository.OpinionRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserProfileMyOpinionsController {
    private SceneManager sceneManager;

    @Autowired
    OpinionRepository opinionRepository;

    @FXML
    private ListView<String> opinionListView;

    @FXML
    public void initialize() {
        List<Opinion> opinions = opinionRepository.findAllByUserOrder_User(getLoggedUser());

        for (Opinion op : opinions) {
            opinionListView.getItems().add(op.getDate().toString()
                    + "  |  Zamówienie nr " + op.getUserOrder().getId().toString()
                    + "  |  " + op.getUserOrder().getCourier()
                    + "  |  Ocena: " + op.getRating()
                    + "/5  |  " + op.getContent());

        }
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
