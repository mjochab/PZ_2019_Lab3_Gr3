package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.EnvelopePricing;
import serwisPaczek.model.PackPricing;
import serwisPaczek.model.PalletPricing;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.EnvelopePricingRepository;
import serwisPaczek.repository.PackPricingRepository;
import serwisPaczek.repository.PalletPricingRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;

import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserCourierCompanyPricingController {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    EnvelopePricingRepository envelopePricingRepository;
    @Autowired
    PackPricingRepository packPricingRepository;
    @Autowired
    PalletPricingRepository palletPricingRepository;
    private SceneManager sceneManager;
    private ApplicationContext context;
    @FXML
    private ListView<String> envelopeList;
    @FXML
    private ListView<String> packList;
    @FXML
    private ListView<String> palletList;
    @FXML
    private Button courierNameButton;
    private Long courierID;

    @FXML
    public void initialize(Long courierID, String courierName) {
        this.courierID = courierID;
        Courier courier = courierRepository.getOne(courierID);

        // Get price lists
        EnvelopePricing envelopePricing = envelopePricingRepository.findByCourier(courier);
        PackPricing packPricing = packPricingRepository.findByCourier(courier);
        PalletPricing palletPricing = palletPricingRepository.findByCourier(courier);

        courierNameButton.setText(courier.getName());
        envelopeList.getItems().addAll("do 1 kg: " + envelopePricing.getUp_to_1() + " zł");
        packList.getItems().addAll("do 1 kg: " + packPricing.getUp_to_1() + " zł");
        packList.getItems().addAll("do 2 kg: " + packPricing.getUp_to_2() + " zł");
        packList.getItems().addAll("do 5 kg: " + packPricing.getUp_to_5() + " zł");
        packList.getItems().addAll("do 10 kg: " + packPricing.getUp_to_10() + " zł");
        packList.getItems().addAll("do 15 kg: " + packPricing.getUp_to_15() + " zł");
        packList.getItems().addAll("do 20 kg: " + packPricing.getUp_to_20() + " zł");
        packList.getItems().addAll("do 30 kg: " + packPricing.getUp_to_30() + " zł");
        palletList.getItems().addAll("do 300 kg: " + palletPricing.getUp_to_300() + " zł");
        palletList.getItems().addAll("do 500 kg: " + palletPricing.getUp_to_500() + " zł");
        palletList.getItems().addAll("do 800 kg: " + palletPricing.getUp_to_800() + " zł");
        palletList.getItems().addAll("do 1000 kg: " + palletPricing.getUp_to_1000() + " zł");
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void CourierOpinion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.other/userCourierOpinions.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            UserCourierOpinionsController userCourierOpinionsController = loader.getController();
            userCourierOpinionsController.initialize(courierID);

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
