package serwisPaczek.controller.user.order;


import generate.GeneratePdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Parcel;
import serwisPaczek.model.UserOrder;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserOrderFinalizeController {

    private SceneManager sceneManager;
    private UserOrder userOrder;
    private Adress received;
    private Adress sender;
    private Parcel parcel;
    private Courier courier;
    @FXML
    private Label TFnr;
    @FXML
    private Label TFcourier;
    @FXML
    private Label TFmoney;
    @FXML
    private Label TFname;
    @FXML
    private Label TFsurname;
    @FXML
    private Label TFstreet;
    @FXML
    private Label TFcity;
    @FXML
    private Label TFhouseNumber;
    @FXML
    private Label TFzipCode;
    @FXML
    private Label TFfromName;
    @FXML
    private Label TFfromSurname;
    @FXML
    private Label TFfromStreet;
    @FXML
    private Label TFfromCity;
    @FXML
    private Label TFfromHouseNumber;
    @FXML
    private Label TFfromZipCode;
    @FXML
    private TextField TFaccountBalace;

    @FXML
    public void initialize(UserOrder userOrder, Adress sender,
                           Adress received, Courier courier, Parcel parcel) {
        this.userOrder = userOrder;
        this.sender = sender;
        this.received = received;
        this.courier = courier;
        this.parcel = parcel;
        TFnr.setText(userOrder.getId().toString());
        TFcourier.setText(courier.getName());
        TFmoney.setText(String.valueOf(userOrder.getPrice()));
        // sender
        TFcity.setText(received.getCity());
        TFzipCode.setText(received.getZipCode());
        TFstreet.setText(received.getStreet());
        TFhouseNumber.setText(received.getHouseNumber().toString());
        TFname.setText(received.getName());
        TFsurname.setText(received.getSurname());
        // receiver
        TFfromSurname.setText(sender.getSurname());
        TFfromName.setText(sender.getName());
        TFfromCity.setText(sender.getCity());
        TFfromHouseNumber.setText(sender.getHouseNumber().toString());
        TFfromStreet.setText(sender.getStreet());
        TFfromZipCode.setText(sender.getZipCode());
        // show acc balance
        TFaccountBalace.setText(String.valueOf(getLoggedUser().getAccountBalance()));

    }

    @FXML
    public void generatePDF() {
        GeneratePdf generatePdf = new GeneratePdf(userOrder.getId(), courier.getName(),
                userOrder.getPrice(), received.getCity(), received.getZipCode(),
                received.getStreet(), sender.getName(), received.getName(),
                userOrder.getDate(), sender.getSurname(), received.getSurname(),
                sender.getCity(), sender.getStreet(), sender.getEmail(), received.getEmail(),
                sender.getTelephoneNumber(), received.getTelephoneNumber(),
                sender.getHouseNumber(), received.getHouseNumber(),
                parcel.getWeight(), parcel.getLength(), parcel.getWidth(),
                parcel.getHeight(), parcel.getType());
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
