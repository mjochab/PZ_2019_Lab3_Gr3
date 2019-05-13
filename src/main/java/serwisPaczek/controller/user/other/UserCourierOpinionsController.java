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

@Controller
public class UserCourierOpinionsController {
    private SceneManager sceneManager;
    @FXML
    private ListView<String> opinionList;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OpinionRepository opinionRepository;


    public void initialize() {
        // Get courier
        Long courierID = UserCourierCompaniesListController.getCourierID();
        Courier courier = courierRepository.getOne(courierID);

        // Get opinions
        Opinion opinions = opinionRepository.findByUserOrder_Courier(courier);

        // Show opinions in opinionList container
        opinionList.getItems().addAll(opinions.getDate() + " " + " |  Ocena: " +
                opinions.getRating() + "  |  " + opinions.getContent());


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
