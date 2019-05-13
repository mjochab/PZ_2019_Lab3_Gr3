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
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.Date;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserOrderFillAddressesFormController {
    private SceneManager sceneManager;

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


    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    private Parcel parcel;
    private Courier courier;
    private float price;
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
    private CheckBox fillAdressCheckbox;

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
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void OpenFinalize(ActionEvent event) throws IOException {
        Adress sender = new Adress(TFname.getText(), TFsurname.getText(), TFspot.getText(),
                TFstreet.getText(), Integer.parseInt(TFhouseNumber.getText()), TFzipCode.getText(),
                Long.parseLong(TFnr.getText()), TFemail.getText());
        Adress received = new Adress(TFsenderName.getText(), TFsenderSurname.getText(),
                TFsenderCity.getText(),
                TFsenderStreet.getText(), Integer.parseInt(TFsenderHouseNumber.getText()), TFsenderZipCode.getText(),
                Long.parseLong(TFsenderNr.getText()), TFsenderEmail.getText());
        adressRepository.save(received);
        adressRepository.save(sender);

        RecipientAdress recipientAdress = recipientAdressRepository.save(new RecipientAdress(received));
        SenderAdress senderAdress = senderAdressRepository.save(new SenderAdress(sender));

        //example add order
        UserOrder order = orderRepository.save(new UserOrder(price, new Date(), getLoggedUser(),
                courierRepository.getOne(5L),
                Status.WYSLANO_ZGLOSZENIE, senderAdress, recipientAdress));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFinalize.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();
            UserOrderFinalizeController finalizeController = loader.getController();
            finalizeController.initialize( order, sender, received, courier,parcel);


            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void initialize(Parcel parcel,
                           Courier courier, float price) {
     this.parcel=parcel;
        this.courier = courier;
        this.price=price;
        fillAdressCheckbox.setVisible(false);
        if (getLoggedUser().getAdress() != null) {
            fillAdressCheckbox.setVisible(true);
        }
    }


    @FXML
    public void fillAdress(ActionEvent event) {
        if (fillAdressCheckbox.isSelected()) {
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

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
