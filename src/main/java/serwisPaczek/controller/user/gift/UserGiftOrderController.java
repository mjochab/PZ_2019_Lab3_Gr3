package serwisPaczek.controller.user.gift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.model.User;
import serwisPaczek.repository.GiftRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import java.util.List;

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
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, String> premiumPointsColumn;

    @FXML
    public void initialize() {
        List<Gift> giftList = giftRepository.findAll();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
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
        User user = getLoggedUser();
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        getLoggedUser().setAccount_balance(getLoggedUser().getAccount_balance()-gift.getPremiumPoints());
        userRepository.save(user);
        premiumPoints.setText(String.valueOf(getLoggedUser().getAccount_balance()));
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
