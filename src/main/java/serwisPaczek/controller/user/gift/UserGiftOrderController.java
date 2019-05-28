package serwisPaczek.controller.user.gift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.repository.*;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;


@Controller
public class UserGiftOrderController {
    @Autowired
    GiftRepository giftRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GiftOrderRepository giftOrderRepository;
    @Autowired
    RecipientAddressRepository recipientaddressRepository;
    @Autowired
    AddressRepository addressRepository;
    private SceneManager sceneManager;
    @FXML
    private Label premiumPoints;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField houseNumberField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TableView<Gift> tableView;
    @FXML
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, String> premiumPointsColumn;

    @FXML
    public void initialize() {
        fillTableView();
        premiumPoints.setText(String.valueOf(getLoggedUser().getPremiumPointsBalance()));
    }


    /**
     * This method is used to send new GiftOrder to database.
     * It creates new address and Recipientaddress based on information from the textfields and then sends data to database.
     */
    @FXML
    public void orderGift(ActionEvent event) {
        if (validateData() == false) return;
        else {
            User user = getLoggedUser();
            Gift gift = tableView.getSelectionModel().getSelectedItem();
            if (getLoggedUser().getPremiumPointsBalance() - gift.getPremiumPoints() < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Za mało punktów premium na koncie!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.show();
                return;
            }
            getLoggedUser().setPremiumPointsBalance(getLoggedUser().getPremiumPointsBalance() - gift.getPremiumPoints());
            userRepository.save(user);
            premiumPoints.setText(String.valueOf(getLoggedUser().getPremiumPointsBalance()));

            List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
            Date date = new Date(System.currentTimeMillis());
            Address adress;
            List<Address> adressList = addressRepository.findAll();
            adress = new Address(nameField.getText(), surnameField.getText(),
                    cityField.getText(), streetField.getText(), Integer.valueOf(houseNumberField.getText()),
                    zipCodeField.getText(), Long.valueOf(telephoneField.getText()),
                    emailField.getText()
            );
            adressList.add(adress);
            addressRepository.saveAll(adressList);
            List<RecipientAddress> recipientAdressList = recipientaddressRepository.findAll();
            RecipientAddress recipientAdress = new RecipientAddress(adress);
            recipientAdressList.add(recipientAdress);
            recipientaddressRepository.saveAll(recipientAdressList);
            GiftOrder giftOrder = new GiftOrder(date, gift, user, Status.WYSLANO_ZGLOSZENIE, recipientAdress);
            giftOrderList.add(giftOrder);
            giftOrderRepository.saveAll(giftOrderList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Zamówienie zostało złożone", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
    }


    /**
     * This method fills forms on page.
     */
    @FXML
    public void fillAdressForm(ActionEvent event) {
        if (getLoggedUser().getAddress() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Brak wypełnionego adresu w profilu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        Address adress = getLoggedUser().getAddress();
        nameField.setText(adress.getName());
        surnameField.setText(adress.getSurname());
        cityField.setText(adress.getCity());
        streetField.setText(adress.getStreet());
        houseNumberField.setText(String.valueOf(adress.getHouseNumber()));
        zipCodeField.setText(adress.getZipCode());
        telephoneField.setText(String.valueOf(adress.getTelephoneNumber()));
        emailField.setText(adress.getEmail());
    }

    @FXML
    public void openMainUserPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * This method fills TableView on page.
     * It checks if the gift is available or not by checking its "status" variable.
     */
    void fillTableView() {
        List<Gift> giftListDefault = giftRepository.findAll();
        List<Gift> giftList = new ArrayList<>();
        for (Gift gift : giftListDefault) {
            if (gift.getStatus().equals("AKTYWNY")) giftList.add(gift);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
    }

    /**
     * This method is used to validate data entered into the adress form
     */
    boolean validateData() {
        StringBuilder message = new StringBuilder();

        if (nameField.getText().length() <= 2) {
            message.append("Imię musi zawierać więcej niż dwa znaki! \n");
        }
        if (!(nameField.getText().matches("[a-zA-Z]+"))) {
            message.append("Imię odbiorcy musi zawierać wyłącznie małe oraz duże litery! \n");
        }
        if (surnameField.getText().length() <= 2) {
            message.append("Nazwisko musi zawierać więcej niż dwa znaki! \n");
        }
        if (!(surnameField.getText().matches("[a-zA-Z]+"))) {
            message.append("Nazwisko musi zawierać wyłącznie małe oraz duże litery!\n");
        }
        if (cityField.getText().length() <= 2) {
            message.append("Nazwa miasta musi zawierać więcej niż dwa znaki!\n");
        }
        if (!cityField.getText().matches("[a-zA-Z]+")) {
            message.append("Nazwa miasta musi posiadać wyłącznie małe oraz duże litery!\n");
        }
        if (!emailField.getText().matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")) {
            message.append("Email musi byc w formacie x@x.pl o długosci pomiędzy 2 a 30 znaków\n");
        }
        if (!zipCodeField.getText().matches("[0-9]{2}+-[0-9]{3}")) {
            message.append("Kod pocztowy musi byc w formule XX-XXX, gdzie X jest cyfrą!\n");
        }
        if (houseNumberField.getText().length() <= 0) {
            message.append("Numer domu nie może być ujemny\n");
        }
        if (!houseNumberField.getText().matches("\\d+")) {
            message.append("Numer domu musi być liczbą!\n");
        }
        if (telephoneField.getText().length() != 9) {
            message.append("Numer telefonu musi posiadać 9 cyfr!\n");
        }
        if (!telephoneField.getText().matches("\\d+")) {
            message.append("Numer telefonu musi składać się wyłącznie z cyfr!\n");
        }
        if (!streetField.getText().matches("[A-Z0-9a-z._%+-]{2,64}")) {
            message.append("Nazwa ulicy musi zawierać się pomiędzy 2 a 64 znakami!\n");
        }
        if (message.length() == 0) return true;
        else {
            showDialog(message.toString());
            return false;
        }
    }
}