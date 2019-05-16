package serwisPaczek.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.service.CourierService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class AdminEditCourierController {
    private SceneManager sceneManager;
    private Courier courier;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    CourierService courierService;
    @FXML
    ComboBox courierComboBox;
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

    public void initialize() {
        List<Courier> courierList = courierRepository.findAll();
        ObservableList<Courier> observableListUsers = FXCollections.observableArrayList(courierList);
        courierComboBox.setItems(observableListUsers);
    }

    @FXML
    public void editCourier(ActionEvent event) {
        try {
            if (courierComboBox.getSelectionModel().isEmpty()) {
                throw new Exception();
            }
            if (this.courier_name.getText().length() > 0
                    && this.envelope_up_to_1.getText().matches("\\d+(\\.\\d{1,2})?")
                    && envelope_up_to_1.getText().length() > 0
                    && this.pack_up_to_1.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_1.getText().length() > 0
                    && this.pack_up_to_2.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_2.getText().length() > 0
                    && this.pack_up_to_5.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_5.getText().length() > 0
                    && this.pack_up_to_10.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_10.getText().length() > 0
                    && this.pack_up_to_15.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_15.getText().length() > 0
                    && this.pack_up_to_20.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_20.getText().length() > 0
                    && this.pack_up_to_30.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pack_up_to_30.getText().length() > 0
                    && this.pallet_up_to_300.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pallet_up_to_300.getText().length() > 0
                    && this.pallet_up_to_500.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pallet_up_to_500.getText().length() > 0
                    && this.pallet_up_to_800.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pallet_up_to_800.getText().length() > 0
                    && this.pallet_up_to_1000.getText().matches("\\d+(\\.\\d{1,2})?")
                    && pallet_up_to_1000.getText().length() > 0) {
                //TODO[P]: create class and obj to hold courier pricing information
                //TODO[P]: block courier
                String courier_name = this.courier_name.getText();
                float envelope_up_to_1 = Float.parseFloat(this.envelope_up_to_1.getText());
                float pack_up_to_1 = Float.parseFloat(this.pack_up_to_1.getText());
                float pack_up_to_2 = Float.parseFloat(this.pack_up_to_2.getText());
                float pack_up_to_5 = Float.parseFloat(this.pack_up_to_5.getText());
                float pack_up_to_10 = Float.parseFloat(this.pack_up_to_10.getText());
                float pack_up_to_15 = Float.parseFloat(this.pack_up_to_15.getText());
                float pack_up_to_20 = Float.parseFloat(this.pack_up_to_20.getText());
                float pack_up_to_30 = Float.parseFloat(this.pack_up_to_30.getText());
                float pallet_up_to_300 = Float.parseFloat(this.pallet_up_to_300.getText());
                float pallet_up_to_500 = Float.parseFloat(this.pallet_up_to_500.getText());
                float pallet_up_to_800 = Float.parseFloat(this.pallet_up_to_800.getText());
                float pallet_up_to_1000 = Float.parseFloat(this.pallet_up_to_1000.getText());

                courierService.editCourierCompany(this.courier, courier_name, envelope_up_to_1, pack_up_to_1, pack_up_to_2, pack_up_to_5,
                        pack_up_to_10, pack_up_to_15, pack_up_to_20, pack_up_to_30, pallet_up_to_300, pallet_up_to_500,
                        pallet_up_to_800, pallet_up_to_1000);
                showDialog("Poprawna edycja kuriera.");
                sceneManager.show(SceneType.ADMIN_EDIT_COURIER);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog("Uzupełnij wszystkie pola cennika poprawnymi danymi." +
                    "\nMaksymalnie dwie cyfry po przecinku." +
                    "\nPrzecinek zapisuj jako kropkę.");
        } catch (Exception e) {
            showDialog("Nie dokonano wyboru kuriera.");
        }
    }

    @FXML
    public void showCourier(ActionEvent event) {
        this.courier = (Courier) courierComboBox.getSelectionModel().getSelectedItem();
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
    }

    @FXML
    public void openAdminMain(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
