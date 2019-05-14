package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Opinion;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.OpinionRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

@Controller
public class UserCourierOpinionsController {
    private SceneManager sceneManager;
    @FXML
    private ListView<String> opinionList;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OpinionRepository opinionRepository;


    @FXML
    public void initialize(Long courierId) {
        Courier courier = courierRepository.getOne(courierId);

        // Get opinions
        List<Opinion> opinions = opinionRepository.findAllByUserOrder_Courier(courier);

        for (Opinion op : opinions) {
            opinionList.getItems().add(op.getDate() + " " + " |  Ocena: " +
                    op.getRating() + "  |  " + op.getContent());
        }
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void PriceList(ActionEvent event) {
        sceneManager.show(SceneType.USER_COURIER_COMPANY_PRICING);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
