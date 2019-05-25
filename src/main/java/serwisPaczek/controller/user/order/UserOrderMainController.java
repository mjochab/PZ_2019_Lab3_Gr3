package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Parcel;
import serwisPaczek.repository.ParcelRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;
import static serwisPaczek.utils.TextFieldUtils.isCorrect;

@Controller
public class UserOrderMainController {
    private SceneManager sceneManager;
    private ApplicationContext context;
    @Autowired
    private ParcelRepository parcelRepository;

    @FXML
    private TextField TFdlugosc;
    @FXML
    private RadioButton RBkoperta;
    @FXML
    private RadioButton RBpaleta;
    @FXML
    private TextField TFszerokosc;
    @FXML
    private TextField TFwysokosc;
    @FXML
    private RadioButton RBpaczka;
    @FXML
    private TextField TFwaga;
    @FXML
    private Label Lwaga;
    @FXML
    private Label Lszerokosc;
    @FXML
    private Label Ldlugosc;
    @FXML
    private Label Lwysokosc;
    @FXML
    public void initialize() {
        Lwaga.setVisible(false);
        Lszerokosc.setVisible(false);
        Lwysokosc.setVisible(false);
        Ldlugosc.setVisible(false);
    }

    /**
     * Part of OrderCourierModule,
     * method used for to complete the shipment data and validate them
     */
    @FXML
    public void openOrderChooseCourierPanel() {
        if (!(RBpaczka.isSelected() || RBkoperta.isSelected() || RBpaleta.isSelected())) {
            showDialog("Wybierz typ paczki");
            return;
        }
        if (isCorrect(TFdlugosc.getText()) && isCorrect(TFszerokosc.getText()) && isCorrect(TFwysokosc.getText()) &&
                isCorrect(TFwaga.getText())) {

            String typ;
            if (RBkoperta.isSelected()) {
                typ = "koperta";
            } else if (RBpaczka.isSelected()) {
                typ = "paczka";
            } else typ = "paleta";

            if((!TFwaga.getText().matches("\\d+")) ){
                Lwaga.setText("Waga musi być liczbą!");
                Lwaga.setVisible(true);
            }
            else {
                Lwaga.setVisible(false);
            }
            if((!TFdlugosc.getText().matches("\\d+")) ){
                Ldlugosc.setText("Długość musi być liczbą!");
                Ldlugosc.setVisible(true);
            }
            else {
                Ldlugosc.setVisible(false);
            }
            if((!TFszerokosc.getText().matches("\\d+")) ){
                Lszerokosc.setText("Szerokość musi być liczbą!");
                Lszerokosc.setVisible(true);
            }
            else {
                Lszerokosc.setVisible(false);
            }
            if((!TFwysokosc.getText().matches("\\d+")) ){
                Lwysokosc.setText("Wysokość musi być liczbą!");
                Lwysokosc.setVisible(true);
            }
            else {
                Lwysokosc.setVisible(false);
            }
    if((TFwysokosc.getText().matches("\\d+") && (TFszerokosc.getText().matches("\\d+")) &&
            (TFdlugosc.getText().matches("\\d+")) && (TFwaga.getText().matches("\\d+")) ) ){

            if (typ == "koperta" && (Integer.parseInt(TFwaga.getText()) > 1 || Integer.parseInt(TFdlugosc.getText()) > 35
                    || Integer.parseInt(TFszerokosc.getText()) > 25 || Integer.parseInt(TFwysokosc.getText()) > 25)) {
                showDialog("Złe wymiary dla koperty!");
                return;
            } else if (typ == "paczka" && (Integer.parseInt(TFwaga.getText()) > 30 || Integer.parseInt(TFdlugosc.getText()) > 100
                    || Integer.parseInt(TFszerokosc.getText()) > 90 || Integer.parseInt(TFwysokosc.getText()) > 70)) {
                showDialog("Złe wymiary dla paczki!");
                return;
            } else if (typ == "paleta" && (Integer.parseInt(TFwaga.getText()) > 100 || Integer.parseInt(TFdlugosc.getText()) > 200
                    || Integer.parseInt(TFszerokosc.getText()) > 140 || Integer.parseInt(TFwysokosc.getText()) > 200)) {
                showDialog("Złe wymiary dla palety!");
                return;
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderChooseCourier.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent root = loader.load();

                UserOrderChooseCourierController chooseCourierController = loader.getController();

                chooseCourierController.initialize(new Parcel(Integer.parseInt(TFdlugosc.getText()),
                        Integer.parseInt(TFszerokosc.getText()),
                        Integer.parseInt(TFwysokosc.getText()),
                        typ, Integer.parseInt(TFwaga.getText())));

                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }}
    else {
        return;
    }
        } else {
            showDialog("Uzupełnij wszystkie pola wymiarów paczki!");
            return;
        }

    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        if (getLoggedUser() == null) {
            sceneManager.show(SceneType.MAIN);
        } else {
            sceneManager.show(SceneType.USER_MAIN);
        }
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