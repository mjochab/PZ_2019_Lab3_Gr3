package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Opinion;
import serwisPaczek.repository.OpinionRepository;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.Date;

@Controller
public class UserOrderAddOpinionController {
    @Autowired
    OpinionRepository opinionRepository;
    @Autowired
    OrderRepository orderRepository;
    private SceneManager sceneManager;
    @FXML
    private Label orderLabel;
    @FXML
    private ComboBox<Integer> opinionComboBox;
    @FXML
    private TextField opinionTextField;

    private Long orderId;

    @FXML
    public void initialize(Long orderID) {
        this.orderId = orderID;

        opinionComboBox.getItems().setAll(1, 2, 3, 4, 5);
        opinionComboBox.getSelectionModel().selectLast();
        orderLabel.setText("Wystaw opinię dla zamówienia nr " + orderID);
        opinionTextField.setText("Super kurier, polecam! ");
    }

    private void alertWarning(String message, Boolean error) {
        Alert alert;
        if (error) {
            alert = new Alert(Alert.AlertType.WARNING,
                    message, ButtonType.OK);
            alert.setTitle("Błąd");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION,
                    message, ButtonType.OK);
            alert.setTitle("Sukces");
        }
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.show();
    }

    /**
     * This method is used to handle adding opinion
     * Takes data from opinionTextField and opinionComboBox
     *
     */
    @FXML
    public void addOpinion(ActionEvent event) {

        if (opinionRepository.findByUserOrder_Id(orderId) == null) {
            if (opinionTextField.getLength() >= 5) {
                Date date = new Date();
                Opinion op = new Opinion(date, opinionTextField.getText(), opinionComboBox.getValue(), orderRepository.getOne(orderId));
                opinionRepository.save(op);
                alertWarning("Pomyślnie dodano opinię", false);
                sceneManager.show(SceneType.USER_PROFILE_MY_OPINIONS);
            } else {
                alertWarning("Opinia powinna mieć minimum 5 znaków!", true);
            }
        } else {
            alertWarning("Dla danego zamówienia już istnieje wystawiona opinia", true);
        }
    }

    @FXML
    public void openUserMain(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
