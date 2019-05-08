package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class UserOrderFinalizeController {
    private SceneManager sceneManager;

    private String typ;
    private String waga;
    private String szerokosc;
    private String wysokosc;
    private String dlugosc;
    private Adress received;
    private Adress sender;

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
    public void initialize(String typ, String waga, String dlugosc, String szerokosc,
                           String wysokosc, Adress sender, Adress received) {
        this.typ = typ;
        this.waga = waga;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.sender = sender;
        this.received = received;

        TFmoney.setText(dlugosc);
        TFcity.setText(received.getCity());
        TFfromCity.setText(sender.getCity());
        TFfromHouseNumber.setText(sender.getHouseNumber().toString());
        TFhouseNumber.setText(received.getHouseNumber().toString());
        TFstreet.setText(received.getStreet());
        TFfromStreet.setText(sender.getStreet());
        TFzipCode.setText(received.getZipCode());
        TFfromZipCode.setText(sender.getZipCode());
    }
}