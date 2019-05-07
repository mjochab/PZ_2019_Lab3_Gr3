package serwisPaczek.controller.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class UserCourierCompanyPricingController {
    private SceneManager sceneManager;
    @FXML
    private ListView<String> envelopeList;
    @FXML
    private ListView<String> packList;
    @FXML
    private ListView<String> palletList;
    @FXML
    private Button courierNameButton;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    EnvelopePricingRepository envelopePricingRepository;
    @Autowired
    PackPricingRepository packPricingRepository;
    @Autowired
    PalletPricingRepository palletPricingRepository;

    public void initialize() {
        Long courierID = UserCourierCompaniesListController.getCourierID();
        Courier courier = courierRepository.getOne(courierID);

        // Get price lists
        EnvelopePricing envelopePricing = envelopePricingRepository.findByCourier(courier);
        PackPricing packPricing = packPricingRepository.findByCourier(courier);
        PalletPricing palletPricing = palletPricingRepository.findByCourier(courier);

        courierNameButton.setText(UserCourierCompaniesListController.getCourierName());
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
        sceneManager.show(SceneType.COURIER_OPINIONS);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
