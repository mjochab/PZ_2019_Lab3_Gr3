package serwisPaczek.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.dto.CourierPricingDto;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.service.CourierService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class AdminEditCourierController {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    CourierService courierService;
    @FXML
    ComboBox courierComboBox;
    private SceneManager sceneManager;
    private Courier courier;
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
    @FXML
    private CheckBox blockCourierCheckBox;

    public void initialize() {
        List<Courier> courierList = courierRepository.findAll();
        ObservableList<Courier> observableListCouriers = FXCollections.observableArrayList(courierList);
        courierComboBox.setItems(observableListCouriers);
    }

    /**
     * This method is used to edit an existing courier company in the database.
     *
     * @return Whether editing a courier company to the database was successful or not.
     */
    @FXML
    public boolean editCourier(ActionEvent event) {
        if (courierComboBox.getSelectionModel().isEmpty()) {
            showDialog("Nie dokonano wyboru kuriera.");
            return false;
        }
        CourierPricingDto courierPricing = new CourierPricingDto(
                courier, courier_name.getText(), blockCourierCheckBox.isSelected(), envelope_up_to_1.getText(),
                pack_up_to_1.getText(), pack_up_to_2.getText(), pack_up_to_5.getText(), pack_up_to_10.getText(),
                pack_up_to_15.getText(), pack_up_to_20.getText(), pack_up_to_30.getText(), pallet_up_to_300.getText(),
                pallet_up_to_500.getText(), pallet_up_to_800.getText(), pallet_up_to_1000.getText());
        boolean isDataCorrect = courierPricing.validate();

        if (!isDataCorrect) {
            showDialog("Uzupełnij wszystkie pola cennika poprawnymi danymi." +
                    "\nMaksymalnie dwie cyfry po przecinku." +
                    "\nPrzecinek zapisuj jako kropkę.");
            return false;
        } else {
            courierService.editCourierCompany(courierPricing);
            showDialog("Poprawna edycja kuriera.");
            sceneManager.show(SceneType.ADMIN_EDIT_COURIER);
            return true;
        }
    }

    /**
     * This method is used to show courier company and it's pricing.
     */
    @FXML
    public void showCourier(ActionEvent event) {
        courier = (Courier) courierComboBox.getSelectionModel().getSelectedItem();
        courier_name.setText(courier.getName());
        envelope_up_to_1.setText(Float.toString(courier.getEnvelopePricing().getUp_to_1()));
        pack_up_to_1.setText(Float.toString(courier.getPackPricing().getUp_to_1()));
        pack_up_to_2.setText(Float.toString(courier.getPackPricing().getUp_to_2()));
        pack_up_to_5.setText(Float.toString(courier.getPackPricing().getUp_to_5()));
        pack_up_to_10.setText(Float.toString(courier.getPackPricing().getUp_to_10()));
        pack_up_to_15.setText(Float.toString(courier.getPackPricing().getUp_to_15()));
        pack_up_to_20.setText(Float.toString(courier.getPackPricing().getUp_to_20()));
        pack_up_to_30.setText(Float.toString(courier.getPackPricing().getUp_to_30()));
        pallet_up_to_300.setText(Float.toString(courier.getPalletPricing().getUp_to_300()));
        pallet_up_to_500.setText(Float.toString(courier.getPalletPricing().getUp_to_500()));
        pallet_up_to_800.setText(Float.toString(courier.getPalletPricing().getUp_to_800()));
        pallet_up_to_1000.setText(Float.toString(courier.getPalletPricing().getUp_to_1000()));
        blockCourierCheckBox.setSelected(courier.is_blocked());
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
