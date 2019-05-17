package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.List;

import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserCourierCompaniesListController {
    private SceneManager sceneManager;
    private ApplicationContext context;
    @Autowired
    CourierRepository courierRepository;
    @FXML
    private GridPane gridPane;

    public void initialize() {
        List<Courier> listCouriers = courierRepository.findAll();
        int index = 0;
        int gridRow = 0;
        int gridCol = 0;
        String color = "";
        int numberOfButtons = (int) courierRepository.count();
        Button[] button;
        button = new Button[numberOfButtons];

        for (Courier courier : listCouriers) {
            if(courier.is_blocked()) {
                continue;
            }
            if (index % 4 == 0) {
                gridRow++;
                gridCol = 0;
                gridPane.addRow(gridRow);
            }
            // btn color switcher
            switch (index % 5) {
                case (0):
                    color = "red";
                    break;
                case (1):
                    color = "darkslategray";
                    break;
                case (2):
                    color = "orange";
                    break;
                case (3):
                    color = "indigo";
                    break;
                case (4):
                    color = "hotpink";
                    break;
            }
            button[index] = new Button(courier.getName());
            button[index].setMinHeight(120);
            button[index].setMinWidth(135);
            button[index].setStyle("-fx-background-radius: 3px; " +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: " + color + ";"
            );

            button[index].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.other/userCourierCompanyPricing.fxml"));
                        loader.setControllerFactory(context::getBean);
                        Parent root = loader.load();
                        UserCourierCompanyPricingController userCourierCompanyPricingController = loader.getController();
                        userCourierCompanyPricingController.initialize(courier.getId());
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            gridPane.add(button[index], gridCol, gridRow);
            gridCol++;
            index++;
        }
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        //TODO[ALAN]: MAIN OR USER_MAIN
        sceneManager.show(SceneType.MAIN);
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
