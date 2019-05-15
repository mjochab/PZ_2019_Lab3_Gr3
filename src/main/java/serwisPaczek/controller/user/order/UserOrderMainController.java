package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;

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
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void OpenPriceListButton(ActionEvent event) {
        if (!(RBpaczka.isSelected() || RBkoperta.isSelected() || RBpaleta.isSelected())) {
            showDialog("Wybierz typ paczki");
            return;
        }
        String typ;
        if (RBkoperta.isSelected()) {
            typ = "koperta";
        } else if (RBpaczka.isSelected()) {
            typ = "paczka";
        } else typ = "paleta";

        Parcel parcel = parcelRepository.save(new Parcel(Integer.parseInt(TFdlugosc.getText()),
                Integer.parseInt(TFszerokosc.getText()),
                Integer.parseInt(TFwysokosc.getText()),
                typ, Integer.parseInt(TFwaga.getText())
        ));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderChooseCourier.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            UserOrderChooseCourierController chooseCourierController = loader.getController();
            chooseCourierController.initialize(parcel);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

}