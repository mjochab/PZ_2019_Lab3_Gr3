package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.EnvelopePricingRepository;
import serwisPaczek.repository.PackPricingRepository;
import serwisPaczek.repository.PalletPricingRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.List;

import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserOrderChooseCourierController {
    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    private SceneManager sceneManager;
    @FXML
    private GridPane gridPane;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    private PackPricingRepository packPricingRepository;
    @Autowired
    private PalletPricingRepository palletPricingRepository;
    @Autowired
    private EnvelopePricingRepository envelopePricingRepository;

    String type;
    String weight;
    String width;
    String height;
    String length;
    int ratio = 1;
    double price;

    @FXML
    private Label test;

    @FXML
    public void OpenFillAdressessForm(ActionEvent actionEvent) {
        if (UserLoginDto.getLoggedUser() != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFillAddressesForm.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent root = loader.load();
                UserOrderFillAddressesFormController fillAdressessController = loader.getController();
                fillAdressessController.initialize(type, weight, length, width, height);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            showDialog("Musisz być zalogowany by dokonać zamówienia!");
        }
    }

    @FXML
    public void initialize(String type, String weight, String length, String width, String height) {
        this.type = type;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;

        if (type == "paczka") {

            if (Integer.parseInt(length) <= 60 && Integer.parseInt(width) <= 50 && Integer.parseInt(height) <= 30)
                ratio = 1;
            else if (Integer.parseInt(length) <= 80 && Integer.parseInt(width) <= 70 && Integer.parseInt(height) <= 50)
                ratio = 2;
            else if (Integer.parseInt(length) <= 100 && Integer.parseInt(width) <= 90 && Integer.parseInt(height) <= 70)
                ratio = 3;
        }
        List<Courier> listCouriers = courierRepository.findAll();

        int index = 0;
        int gridRow = 0;
        int gridCol = 0;
        String color = "";
        int numberOfButtons = (int) courierRepository.count();
        Button[] button;
        button = new Button[numberOfButtons];

        for (Courier courier : listCouriers) {

            if (type == "koperta") {
                price = envelopePricingRepository.findByCourier(courier).getUp_to_1();
            } else if (type == "paczka") {

                if (Integer.parseInt(weight) <= 1) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_1() * ratio;
                }
                if (Integer.parseInt(weight) <= 2) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_2() * ratio;
                }
                if (Integer.parseInt(weight) <= 5) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_5() * ratio;
                }
                if (Integer.parseInt(weight) <= 10) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_10() * ratio;
                }
                if (Integer.parseInt(weight) <= 15) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_15() * ratio;
                }
                if (Integer.parseInt(weight) <= 20) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_20() * ratio;
                }
                if (Integer.parseInt(weight) <= 30) {
                    price = packPricingRepository.findByCourier(courier).getUp_to_30() * ratio;
                }
            } else if (type == "paleta") {
                if (Integer.parseInt(weight) <= 300) {
                    price = palletPricingRepository.findByCourier(courier).getUp_to_300();
                } else if (Integer.parseInt(weight) <= 500) {
                    price = palletPricingRepository.findByCourier(courier).getUp_to_500();
                } else if (Integer.parseInt(weight) <= 800) {
                    price = palletPricingRepository.findByCourier(courier).getUp_to_800();
                } else if (Integer.parseInt(weight) <= 1000) {
                    price = palletPricingRepository.findByCourier(courier).getUp_to_1000();
                }
            }

            if (index % 4 == 0) {
                gridRow++;
                gridCol = 0;
                gridPane.addRow(gridRow);
            }
            // Btn color switcher
            switch (index % 5) {
                case (0):
                    color = "red";
                    break;
                case (1):
                    color = "darkslategray";
                    break;
                case (2):
                    color = "orange";
                    break;
                case (3):
                    color = "indigo";
                    break;
                case (4):
                    color = "hotpink";
                    break;
            }
            button[index] = new Button(courier.getName() + price + "zł");
            button[index].setMinHeight(120);
            button[index].setMinWidth(135);
            button[index].setStyle("-fx-background-radius: 3px; " +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: " + color + ";"
            );

            button[index].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFillAddressesForm.fxml"));
                        loader.setControllerFactory(context::getBean);
                        Parent root = loader.load();
                        UserOrderFillAddressesFormController fillAdressessController = loader.getController();
                        stage.setScene(new Scene(root));
                        stage.show();

                        fillAdressessController.initialize(type, weight, length, width, height);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            gridPane.add(button[index], gridCol, gridRow);
            gridCol++;
            index++;
        }
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
