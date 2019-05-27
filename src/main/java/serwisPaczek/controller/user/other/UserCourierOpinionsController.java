package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Opinion;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.OpinionRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.List;

import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserCourierOpinionsController {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OpinionRepository opinionRepository;
    private SceneManager sceneManager;
    private ApplicationContext context;
    @FXML
    private ListView<String> opinionList;
    @FXML
    private Button buttonCourier;
    private Long courierID;

    @FXML
    public void initialize(Long courierId) {
        this.courierID = courierId;
        Courier courier = courierRepository.getOne(courierId);
        buttonCourier.setText("Opinie dla " + courier.getName());
        List<Opinion> opinions = opinionRepository.findAllByUserOrder_Courier(courier);  // get opinions

        for (Opinion op : opinions) {
            opinionList.getItems().add(op.getDate() + " " + " |  Ocena: " +
                    op.getRating() + "  |  " + op.getContent());
        }
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void openCourierCompanyPricingPanel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.other/userCourierCompanyPricing.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();
            UserCourierCompanyPricingController userCourierCompanyPricingController = loader.getController();
            userCourierCompanyPricingController.initialize(courierID);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
