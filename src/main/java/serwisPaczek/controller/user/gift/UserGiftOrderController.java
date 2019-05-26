package serwisPaczek.controller.user.gift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.model.dto.UserOrderDto;
import serwisPaczek.repository.GiftOrderRepository;
import serwisPaczek.repository.GiftRepository;
import serwisPaczek.repository.RecipientAdressRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static serwisPaczek.model.Status.ANULOWANO;
import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserGiftOrderController {
    private SceneManager sceneManager;

    @Autowired
    GiftRepository giftRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GiftOrderRepository giftOrderRepository;
    @Autowired
    RecipientAdressRepository recipientAdressRepository;
    @Autowired
    AdressRepository adressRepository;

    @FXML
    private Label premiumPoints;
    @FXML
    private ComboBox giftComboBox;
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
        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        List<GiftOrder> giftOrdersList = new ArrayList<>();
        for(GiftOrder giftOrder : giftOrderList){
            if(giftOrder.getUser() == getLoggedUser() && giftOrder.getStatus().toString() == "WYSLANO_ZGLOSZENIE"){
                giftOrdersList.add(giftOrder);
            }
        }
        ObservableList<GiftOrder> observableListGiftOrders = FXCollections.observableArrayList(giftOrdersList);
        giftComboBox.setItems(observableListGiftOrders);
    }

    public void showGiftOrders(ActionEvent event) {
        GiftOrder giftOrder = (GiftOrder)giftComboBox.getSelectionModel().getSelectedItem();
    }

    /**
     * This method is used to send new GiftOrder to database.
     * It creates new Adress and RecipientAdress based on information from the textfields and then sends data to database.
     */
    @FXML
    public void orderGift(ActionEvent event){
        User user = getLoggedUser();
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        if (getLoggedUser().getPremiumPointsBalance()-gift.getPremiumPoints()<0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Za mało punktów premium na koncie!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        getLoggedUser().setPremiumPointsBalance(getLoggedUser().getPremiumPointsBalance()-gift.getPremiumPoints());
        userRepository.save(user);
        premiumPoints.setText(String.valueOf(getLoggedUser().getPremiumPointsBalance()));

        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        Date date = new Date(System.currentTimeMillis());
        Adress adress;
        List<Adress> adressList = adressRepository.findAll();
        adress = new Adress(nameField.getText(), surnameField.getText(),
                cityField.getText(), streetField.getText(), Integer.valueOf(houseNumberField.getText()),
                zipCodeField.getText(), Long.valueOf(telephoneField.getText()),
                emailField.getText()
        );
        adressList.add(adress);
        adressRepository.saveAll(adressList);
        List<RecipientAdress> recipientAdressList = recipientAdressRepository.findAll();
        RecipientAdress recipientAdress = new RecipientAdress(adress);
        recipientAdressList.add(recipientAdress);
        recipientAdressRepository.saveAll(recipientAdressList);
        GiftOrder giftOrder = new GiftOrder(date, gift, user, Status.WYSLANO_ZGLOSZENIE, recipientAdress);
        giftOrderList.add(giftOrder);
        giftOrderRepository.saveAll(giftOrderList);

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Zamówienie zostało złożone", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
    }



    @FXML
    public void cancelGift(ActionEvent event) {
        if (giftComboBox.getSelectionModel().isEmpty()) {
            showDialog("Nie dokonano wyboru prezentu.");
        } else{
            GiftOrder giftOrder = (GiftOrder)giftComboBox.getSelectionModel().getSelectedItem();
            giftOrder.setStatus(ANULOWANO);
            giftOrderRepository.save(giftOrder);
            User user = getLoggedUser();
            user.setPremiumPointsBalance(user.getPremiumPointsBalance() + giftOrder.getGift().getPremiumPoints());
            userRepository.save(user);
            showDialog("Anulowano zamówienie prezentu.");
        }
    }

    /**
     * This method fills forms on page.
     */
    @FXML
    public void fillAdressForm(ActionEvent event){
        Adress adress = getLoggedUser().getAdress();
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
    void fillTableView(){
        List<Gift> giftListDefault = giftRepository.findAll();
        List<Gift> giftList = new ArrayList<>();
        for (Gift gift : giftListDefault){
            if (gift.getStatus().equals("AKTYWNY")) giftList.add(gift);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
    }
}