package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.repository.*;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.SceneManager.stage;
import static serwisPaczek.utils.TextFieldUtils.*;

@Controller
public class UserOrderFillAddressesFormController {
    private SceneManager sceneManager;
    private ApplicationContext context;
    private Parcel parcel;
    private Courier courier;
    private float price;

    @Autowired
    private UserService userService;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private SenderAdressRepository senderAdressRepository;
    @Autowired
    private RecipientAdressRepository recipientAdressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CourierRepository courierRepository;

    @FXML
    private TextField TFsenderSurname;
    @FXML
    private TextField TFhouseNumber;
    @FXML
    private TextField TFsenderHouseNumber;
    @FXML
    private TextField TFsenderStreet;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFzipCode;
    @FXML
    private CheckBox fillAddressCheckbox;
    @FXML
    private TextField TFnr;
    @FXML
    private TextField TFsenderCity;
    @FXML
    private TextField TFspot;
    @FXML
    private TextField TFsenderName;
    @FXML
    private TextField TFsenderEmail;
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFstreet;
    @FXML
    private TextField TFsenderNr;
    @FXML
    private TextField TFsurname;
    @FXML
    private TextField TFsenderZipCode;

    @FXML
    public void initialize(Parcel parcel,
                           Courier courier, float price) {
        this.parcel = parcel;
        this.courier = courier;
        this.price = price;
        fillAddressCheckbox.setVisible(false);
        if (getLoggedUser().getAdress() != null) {
            fillAddressCheckbox.setVisible(true);
        }
    }

    @FXML
    public void openFinalizePanel(ActionEvent event) throws IOException {
        if (isCorrectAndNoDigit(TFname.getText()) && isCorrectAndNoDigit(TFsurname.getText())
                && isCorrectAndNoDigit(TFspot.getText()) &&
                isCorrectAndNoDigit(TFstreet.getText()) &&
                isCorrectAndOnlyDigit(TFhouseNumber.getText()) && isCorrect(TFzipCode.getText()) &&
                isCorrectAndOnlyDigit(TFnr.getText()) &&
                isCorrect(TFemail.getText()) &&
                isCorrectAndNoDigit(TFsenderName.getText()) && isCorrectAndNoDigit(TFsenderSurname.getText())
                && isCorrectAndNoDigit(TFsenderCity.getText()) &&
                isCorrectAndNoDigit(TFsenderStreet.getText()) &&
                isCorrectAndOnlyDigit(TFsenderHouseNumber.getText()) && isCorrect(TFsenderZipCode.getText()) &&
                isCorrectAndOnlyDigit(TFsenderNr.getText()) &&
                isCorrect(TFsenderEmail.getText())) {

            Adress sender = new Adress(TFname.getText(), TFsurname.getText(), TFspot.getText(), TFstreet.getText(),
                    Integer.parseInt(TFhouseNumber.getText()), TFzipCode.getText(), Long.parseLong(TFnr.getText()),
                    TFemail.getText());
            Adress received = new Adress(TFsenderName.getText(), TFsenderSurname.getText(), TFsenderCity.getText(),
                    TFsenderStreet.getText(), Integer.parseInt(TFsenderHouseNumber.getText()), TFsenderZipCode.getText(),
                    Long.parseLong(TFsenderNr.getText()), TFsenderEmail.getText());
            adressRepository.save(received);
            adressRepository.save(sender);

            RecipientAdress recipientAdress = recipientAdressRepository.save(new RecipientAdress(received));
            SenderAdress senderAdress = senderAdressRepository.save(new SenderAdress(sender));
            // example add order
            UserOrder order = orderRepository.save(new UserOrder(price, new Date(), getLoggedUser(),
                    courierRepository.getOne(5L),
                    Status.WYSLANO_ZGLOSZENIE, senderAdress, recipientAdress));


            userService.withdrawFunds(getLoggedUser(), (double) price);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFinalize.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent root = loader.load();
                UserOrderFinalizeController finalizeController = loader.getController();
                finalizeController.initialize(order, sender, received, courier, parcel);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            showDialog("Pola nie zostały poprawnie wypełnione!");
            return;
        }
    }
    @FXML
    public void fillAddress(ActionEvent event) {
        if (fillAddressCheckbox.isSelected()) {
            TFname.setText(getLoggedUser().getAdress().getName());
            TFsurname.setText(getLoggedUser().getAdress().getSurname());
            TFstreet.setText(getLoggedUser().getAdress().getStreet());
            TFnr.setText(getLoggedUser().getAdress().getTelephoneNumber().toString());
            TFemail.setText(getLoggedUser().getAdress().getEmail());
            TFhouseNumber.setText(getLoggedUser().getAdress().getHouseNumber().toString());
            TFspot.setText(getLoggedUser().getAdress().getCity());
            TFzipCode.setText(getLoggedUser().getAdress().getZipCode());
        } else {
            TFname.setText("");
            TFsurname.setText("");
            TFstreet.setText("");
            TFnr.setText("");
            TFemail.setText("");
            TFhouseNumber.setText("");
            TFspot.setText("");
            TFzipCode.setText("");
        }
    }

    @FXML
    public void openUserMain(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
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
