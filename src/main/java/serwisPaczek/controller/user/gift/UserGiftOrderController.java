package serwisPaczek.controller.user.gift;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.repository.GiftRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserGiftOrderController {
    private SceneManager sceneManager;

    @Autowired
    GiftRepository giftRepository;
    @Autowired
    UserRepository userRepository;

    @FXML
    private Label premiumPoints;
    @FXML
    private TableView<Gift> tableView;
    @FXML
    private TableColumn<Gift, String> idColumn;
    @FXML
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, String> premiumPointsColumn;

    @FXML
    public void initialize() {
//        List<Gift> listGifts = giftRepository.findAll();
//        List<Gift> gifts = new ArrayList<>();
//        for (Gift gift : listGifts) {
//        }
//
//
//        idColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("id"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
//        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));
//
//        ObservableList<Gift> observableListUsers = FXCollections.observableArrayList(gifts);
//        tableView.setItems(observableListUsers);
        premiumPoints.setText(String.valueOf(getLoggedUser().getAccount_balance()));
    }

    @FXML
    public void orderGift(ActionEvent event){
       System.out.print(getLoggedUser().getAccount_balance());
    }

    @FXML
    public void openMainUserPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
