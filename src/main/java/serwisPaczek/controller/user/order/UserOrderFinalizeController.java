package serwisPaczek.controller.user.order;

import com.itextpdf.text.*;

import generate.GeneratePdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Parcel;
import serwisPaczek.model.UserOrder;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;


@Controller
public class UserOrderFinalizeController {
    private SceneManager sceneManager;

    private UserOrder userOrder;
    private Adress received;
    private Adress sender;
    private Parcel parcel;
    private Courier courier;
    @FXML
    private Label TFfromHouseNumber;

    @FXML
    private Label TFcourier;

    @FXML
    private Label TFhouseNumber;

    @FXML
    private Label TFfromSurname;

    @FXML
    private Label TFzipCode;

    @FXML
    private Label TFfromZipCode;

    @FXML
    private Label TFfromStreet;

    @FXML
    private Label TFfromCity;

    @FXML
    private Label TFnr;

    @FXML
    private Label TFname;

    @FXML
    private Label TFstreet;

    @FXML
    private Label TFfromName;

    @FXML
    private Label TFmoney;

    @FXML
    private Label TFcity;

    @FXML
    private Label TFsurname;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(UserOrder userOrder, Adress sender,
                           Adress received, Courier courier, Parcel parcel) {

      this.userOrder = userOrder;
        this.sender = sender;
        this.received = received;
    this.courier=courier;
    this.parcel=parcel;
        TFnr.setText(userOrder.getId().toString());
        TFcourier.setText(courier.getName());
        TFmoney.setText(String.valueOf(userOrder.getPrice()));

        TFcity.setText(received.getCity());
        TFzipCode.setText(received.getZipCode());
        TFstreet.setText(received.getStreet());
        TFhouseNumber.setText(received.getHouseNumber().toString());
        TFname.setText(received.getName());
        TFsurname.setText(received.getSurname());

        TFfromSurname.setText(sender.getSurname());
        TFfromName.setText(sender.getName());
        TFfromCity.setText(sender.getCity());
        TFfromHouseNumber.setText(sender.getHouseNumber().toString());
        TFfromStreet.setText(sender.getStreet());
        TFfromZipCode.setText(sender.getZipCode());

    }


    private static Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 24,
            Font.BOLD);
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML
    public void generatePDF(){
        GeneratePdf generatePdf = new GeneratePdf(userOrder.getId(), courier.getName(),
                userOrder.getPrice(), received.getCity(), received.getZipCode(),
                received.getStreet(), sender.getName(), received.getName(),
                userOrder.getDate(), sender.getSurname(), received.getSurname(),
                sender.getCity(), sender.getStreet(), sender.getEmail(), received.getEmail(),
                sender.getTelephoneNumber(), received.getTelephoneNumber(),
                sender.getHouseNumber(), received.getHouseNumber(),
                parcel.getWeight(), parcel.getLength(), parcel.getWidth(),
                parcel.getHeight(), parcel.getType());
    }}
