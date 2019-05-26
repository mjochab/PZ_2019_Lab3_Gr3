package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Coupon;
import serwisPaczek.model.Parcel;
import serwisPaczek.repository.CouponRepository;
import serwisPaczek.repository.ParcelRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;
import static serwisPaczek.utils.TextFieldUtils.isCorrect;

@Controller
public class UserOrderMainController {
    public float discountRatio = 1;

    private SceneManager sceneManager;
    private ApplicationContext context;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private CouponRepository couponRepository;

    @FXML
    private TextField enterCouponName;
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
    private Button addCouponButton;
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
    @FXML
    public void openOrderChooseCourierPanel(ActionEvent event) {
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
                        typ, Integer.parseInt(TFwaga.getText())), discountRatio);


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

    /**
     * This method checks if coupon named like text user entered really exists.
     * It paints textField on green or red depending on the result.
     * Also, it changes value of discountRatio that is later being sent to the next page.
     */
    @FXML
    public void addCoupon(){
        String couponName = enterCouponName.getText();
        List<Coupon> couponList = couponRepository.findAll();
        for (Coupon coupon : couponList){
            if (coupon.getName().equals(couponName)){
                discountRatio=(1-(float)coupon.getDiscount()/100);
                Paint colour = Paint.valueOf("00FF00");
                enterCouponName.setBackground(new Background(new BackgroundFill(colour, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            } else {
                Paint colour = Paint.valueOf("FF0000");
                enterCouponName.setBackground(new Background(new BackgroundFill(colour, CornerRadii.EMPTY, Insets.EMPTY)));
            }
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