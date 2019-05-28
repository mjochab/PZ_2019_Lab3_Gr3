package serwisPaczek.controller.user.gift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.GiftOrder;
import serwisPaczek.model.User;
import serwisPaczek.model.dto.GiftOrderDto;
import serwisPaczek.repository.GiftOrderRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.Status.ANULOWANO;
import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserCancelGiftOrderController {
    @Autowired
    GiftOrderRepository giftOrderRepository;
    @Autowired
    UserRepository userRepository;
    private SceneManager sceneManager;
    @FXML
    private TableView<GiftOrderDto> tableView;
    @FXML
    private TableColumn<GiftOrderDto, String> idColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> giftNameColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> dateColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> statusColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> nameColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> surnameColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> cityColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> streetColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> houseNumberColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> zipCodeColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> telephoneNumberColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> emailColumn;

    /**
     * This method fills TableView on page with data.
     */
    @FXML
    public void initialize() {
        List<GiftOrder> listGiftOrders = giftOrderRepository.findAll();
        List<GiftOrderDto> giftOrderDtos = new ArrayList<>();

        for (GiftOrder giftOrder : listGiftOrders) {
            if (giftOrder.getUser().getId() == getLoggedUser().getId()) {
                giftOrderDtos.add(new GiftOrderDto(giftOrder.getId(),
                        giftOrder.getGift().getName(),
                        giftOrder.getRecipientAddress().getAddress().getName(),
                        giftOrder.getRecipientAddress().getAddress().getSurname(),
                        giftOrder.getRecipientAddress().getAddress().getCity(),
                        giftOrder.getRecipientAddress().getAddress().getStreet(),
                        giftOrder.getRecipientAddress().getAddress().getHouseNumber(),
                        giftOrder.getRecipientAddress().getAddress().getZipCode(),
                        giftOrder.getRecipientAddress().getAddress().getTelephoneNumber(),
                        giftOrder.getRecipientAddress().getAddress().getEmail(),
                        giftOrder.getDate().toString(),
                        giftOrder.getUser().getUsername(),
                        giftOrder.getStatus().toString()
                ));
            }
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("id"));
        giftNameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("giftName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("status"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("zipCode"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("email"));
        ObservableList<GiftOrderDto> observableListGiftOrders = FXCollections.observableArrayList(giftOrderDtos);
        tableView.setItems(observableListGiftOrders);
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
    }

    /**
     * This method is used to cancel GiftOrder.
     */
    @FXML
    public void cancelGiftOrder(ActionEvent event) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
            GiftOrder giftOrder = giftOrderRepository.getOne(selectedId);
            if (giftOrder.getStatus().toString() == "WYSLANO_ZGLOSZENIE") {
                giftOrder.setStatus(ANULOWANO);
                giftOrderRepository.save(giftOrder);
                User user = getLoggedUser();
                user.setPremiumPointsBalance(user.getPremiumPointsBalance() + giftOrder.getGift().getPremiumPoints());
                userRepository.save(user);
                showDialog("Anulowano zamówienie prezentu");
            } else {
                showDialog("Nie można anulować zamówienia prezentu");
            }
        } catch (Exception e) {
            showDialog("Zaznacz zamówienie, które chcesz anulować!");
        }
    }

    @FXML
    public void openUserMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}