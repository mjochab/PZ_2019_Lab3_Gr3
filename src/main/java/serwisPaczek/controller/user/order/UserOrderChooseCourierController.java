package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Parcel;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.EnvelopePricingRepository;
import serwisPaczek.repository.PackPricingRepository;
import serwisPaczek.repository.PalletPricingRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserOrderChooseCourierController {

    private SceneManager sceneManager;
    private int ratio = 1;
    float price =0;
    private ApplicationContext context;

    @Autowired
    CourierRepository courierRepository;
    @Autowired
    private PackPricingRepository packPricingRepository;
    @Autowired
    private PalletPricingRepository palletPricingRepository;
    @Autowired
    private EnvelopePricingRepository envelopePricingRepository;
    @FXML
    private GridPane gridPane;

    /**
     * This method is used to display all existing, non blocked courier companies and their prices for chosen parcel.
     */

    @FXML
    public void initialize(Parcel parcel) {
        List<Courier> listCouriers = courierRepository.findAll();
        int index = 0;
        int gridRow = 0;
        int gridCol = 0;
        String color = "";
        int numberOfButtons = (int) courierRepository.count();
        Button[] button;
        button = new Button[numberOfButtons];

        if (parcel.getType().equals("paczka")) {
            ratio = getRatioByParcelParameters(parcel.getLength(),parcel.getWeight(),parcel.getHeight());
        }

        for (Courier courier : listCouriers) {
            if (courier.is_blocked()) {
                continue;
            }

            if (parcel.getType().equals("koperta")) {
                price = envelopePricingRepository.findByCourier(courier).getUp_to_1();
            }
            else if (parcel.getType().equals("paczka")) {
               price = getPackagePriceByWeightAndCourier(courier, parcel.getWeight(),
                               getRatioByParcelParameters(parcel.getLength(),parcel.getWeight(),parcel.getHeight()));
            }

            else if (parcel.getType().equals("paleta")) {
                price = getPalettePriceByWeightAndCourier(courier, parcel.getWeight());
            }

            if (index % 4 == 0) {
                gridRow++;
                gridCol = 0;
                gridPane.addRow(gridRow);
            }
            // btn color switcher
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

            button[index] = new Button(courier.getName() + "\n" + price + "zł");
            button[index].setMinHeight(120);
            button[index].setMinWidth(135);
            button[index].setStyle("-fx-background-radius: 3px; " +
                    "-fx-text-fill: white;" +
                    "-fx-text-alignment: center;" +
                    "-fx-background-color: " + color + ";"
            );

            float finalPrice = price;
            button[index].setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * This method is used to handle btn click.
                 * After user click btn this method redirect him to userOrderFillAddressesForm.fxml
                 * This method also pass parcel, courier and price information to UserOrderFillAddressesFormController.
                 */
                @Override
                public void handle(ActionEvent event) {
                    if (getLoggedUser() != null) {
                        try {
                                if (getLoggedUser().getAccountBalance() < finalPrice) {
                                    throw new ArithmeticException();
                                }

                                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                        "/fxml/user.order/userOrderFillAddressesForm.fxml"));
                                loader.setControllerFactory(context::getBean);
                                Parent root = loader.load();
                                UserOrderFillAddressesFormController fillAdressessController = loader.getController();
                                fillAdressessController.initialize(parcel, courier, finalPrice);
                                stage.setScene(new Scene(root));
                                stage.show();
                            }
                         catch (IOException e) {
                            showDialog("Musisz być zalogowany by dokonać zamówienia!");
                        } catch (ArithmeticException e) {
                            showDialog("Nie wystarczająca ilość środków na koncie!\n"
                                    + "Stan konta: " + getLoggedUser().getAccountBalance() + " PLN.");
                        }
                    } else showDialog("Musisz być zalogowany by dokonać zamówienia!");
                }
            });
            gridPane.add(button[index], gridCol, gridRow);
            gridCol++;
            index++;
        }
    }

    //to test
    private int getRatioByParcelParameters(int length, int weight, int height){
        if (length <= 60 && weight <= 50 && height <= 30)
            ratio = 1;
        else if (length <= 80 && weight <= 70 && height <= 50)
            ratio = 2;
        else if (length <= 100 && weight <= 90 && height <= 70)
            ratio = 3;
        return ratio;
    }

    //to test
    private float getPackagePriceByWeightAndCourier(Courier courier, int weight, int ratio){

        if (weight <= 1) {
            price = packPricingRepository.findByCourier(courier).getUp_to_1() * ratio;
        }
        if (weight <= 2) {
            price = packPricingRepository.findByCourier(courier).getUp_to_2() * ratio;
        }
        if (weight <= 5) {
            price = packPricingRepository.findByCourier(courier).getUp_to_5() * ratio;
        }
        if (weight <= 10) {
            price = packPricingRepository.findByCourier(courier).getUp_to_10() * ratio;
        }
        if (weight <= 15) {
            price = packPricingRepository.findByCourier(courier).getUp_to_15() * ratio;
        }
        if (weight <= 20) {
            price = packPricingRepository.findByCourier(courier).getUp_to_20() * ratio;
        }
        if (weight <= 30) {
            price = packPricingRepository.findByCourier(courier).getUp_to_30() * ratio;
        }

        return price;
    }

    //to test
    private float getPalettePriceByWeightAndCourier(Courier courier, int weight) {

        if (weight <= 300) {
            price = palletPricingRepository.findByCourier(courier).getUp_to_300();
        } else if (weight <= 500) {
            price = palletPricingRepository.findByCourier(courier).getUp_to_500();
        } else if (weight <= 800) {
            price = palletPricingRepository.findByCourier(courier).getUp_to_800();
        } else if (weight <= 1000) {
            price = palletPricingRepository.findByCourier(courier).getUp_to_1000();
        }

        return price;
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
