package serwisPaczek.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.dto.CourierPricingDto;
import serwisPaczek.service.CourierService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class AdminAddCourierController {
    private SceneManager sceneManager;
    @Autowired
    private CourierService courierService;
    @FXML
    private TextField courier_name;
    @FXML
    private TextField envelope_up_to_1;
    @FXML
    private TextField pack_up_to_1;
    @FXML
    private TextField pack_up_to_2;
    @FXML
    private TextField pack_up_to_5;
    @FXML
    private TextField pack_up_to_10;
    @FXML
    private TextField pack_up_to_15;
    @FXML
    private TextField pack_up_to_20;
    @FXML
    private TextField pack_up_to_30;
    @FXML
    private TextField pallet_up_to_300;
    @FXML
    private TextField pallet_up_to_500;
    @FXML
    private TextField pallet_up_to_800;
    @FXML
    private TextField pallet_up_to_1000;

    /**
     * This method is used to create a new courier company in the database.
     *
     * @return Whether adding a courier company to the database was successful or not.
     */
    @FXML
    public boolean addCourier(ActionEvent event) {
        CourierPricingDto courierPricing = new CourierPricingDto(
                courier_name.getText(), envelope_up_to_1.getText(), pack_up_to_1.getText(), pack_up_to_2.getText(),
                pack_up_to_5.getText(), pack_up_to_10.getText(), pack_up_to_15.getText(), pack_up_to_20.getText(),
                pack_up_to_30.getText(), pallet_up_to_300.getText(), pallet_up_to_500.getText(),
                pallet_up_to_800.getText(), pallet_up_to_1000.getText());
        boolean isDataCorrect = courierPricing.validate();

        if (!isDataCorrect) {
            showDialog("Uzupełnij wszystkie pola cennika poprawnymi danymi." +
                    "\nMaksymalnie dwie cyfry po przecinku." +
                    "\nPrzecinek zapisuj jako kropkę.");
            return false;
        } else {
            courierService.createCourierCompany(courierPricing);
            showDialog("Poprawnie dodano kuriera do bazy.");
            sceneManager.show(SceneType.ADMIN_ADD_COURIER);
            return true;
        }
    }

    @FXML
    public void openAdminMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}