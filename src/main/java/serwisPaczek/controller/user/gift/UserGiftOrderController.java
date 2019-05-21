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
import serwisPaczek.repository.GiftOrderRepository;
import serwisPaczek.repository.GiftRepository;
import serwisPaczek.repository.RecipientAdressRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.sql.Date;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

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
    private CheckBox adressBox;
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
        List<Gift> giftList = giftRepository.findAll();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
        premiumPoints.setText(String.valueOf(getLoggedUser().getAccountBalance()));
    }

    @FXML
    public void orderGift(ActionEvent event){
        User user = getLoggedUser();
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        getLoggedUser().setAccountBalance(getLoggedUser().getAccountBalance()-gift.getPremiumPoints());
        userRepository.save(user);
        premiumPoints.setText(String.valueOf(getLoggedUser().getAccountBalance()));

        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        Date date = new Date(System.currentTimeMillis());
        Adress adress;
        if (adressBox.isSelected()){
            List<Adress> adressList = adressRepository.findAll();
            adress = new Adress(nameField.getText(), surnameField.getText(),
                    cityField.getText(), streetField.getText(), Integer.valueOf(houseNumberField.getText()),
                    zipCodeField.getText(), Long.valueOf(telephoneField.getText()),
                    emailField.getText()
            );
            adressList.add(adress);
            adressRepository.saveAll(adressList);
        }
        else adress = getLoggedUser().getAdress();
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
    public void openMainUserPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
