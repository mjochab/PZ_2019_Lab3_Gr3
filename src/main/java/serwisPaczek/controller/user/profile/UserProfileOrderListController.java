package serwisPaczek.controller.user.profile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.controller.user.order.UserOrderAddOpinionController;
import serwisPaczek.controller.user.order.UserOrderFinalizeController;
import serwisPaczek.model.Address;
import serwisPaczek.model.User;
import serwisPaczek.model.UserOrder;
import serwisPaczek.model.dto.UserOrderDto;
import serwisPaczek.repository.*;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.Status.ANULOWANO;
import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;


@SuppressWarnings("Duplicates")
@Controller
public class UserProfileOrderListController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OpinionRepository opinionRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    RecipientAddressRepository recipientAddressRepository;
    @Autowired
    SenderAddressRepository senderAddressRepository;
    private SceneManager sceneManager;
    @FXML
    private TableView<UserOrderDto> tableView;
    @FXML
    private TableColumn<UserOrderDto, String> idColumn;
    @FXML
    private TableColumn<UserOrderDto, String> courierColumn;
    @FXML
    private TableColumn<UserOrderDto, String> dateColumn;
    @FXML
    private TableColumn<UserOrderDto, String> statusColumn;
    @FXML
    private TableColumn<UserOrderDto, String> nameColumn;
    @FXML
    private TableColumn<UserOrderDto, String> senderNameColumn;
    @FXML
    private TableColumn<UserOrderDto, String> surnameColumn;
    @FXML
    private TableColumn<UserOrderDto, String> senderSurnameColumn;
    @FXML
    private TableColumn<UserOrderDto, String> cityColumn;
    @FXML
    private TableColumn<UserOrderDto, String> streetColumn;
    @FXML
    private TableColumn<UserOrderDto, String> houseNumberColumn;
    @FXML
    private TableColumn<UserOrderDto, String> zipCodeColumn;
    @FXML
    private TableColumn<UserOrderDto, String> telephoneNumberColumn;
    @FXML
    private TableColumn<UserOrderDto, String> emailColumn;
    @FXML
    private Button btnAddOpinion;

    private ApplicationContext context;
    private Long orderID;

    /**
     * This method fills TableView on page with data.
     */
    @FXML
    public void initialize() {
        List<UserOrder> listOrders = orderRepository.findAll();
        List<UserOrderDto> userOrderDtos = new ArrayList<>();

        for (UserOrder order : listOrders) {
            if (order.getUser().getId() == getLoggedUser().getId()) {
                userOrderDtos.add(new UserOrderDto(order.getId(),
                        order.getRecipientAddress().getAddress().getName(),
                        order.getRecipientAddress().getAddress().getSurname(),
                        order.getRecipientAddress().getAddress().getCity(),
                        order.getRecipientAddress().getAddress().getStreet(),
                        order.getRecipientAddress().getAddress().getHouseNumber(),
                        order.getRecipientAddress().getAddress().getZipCode(),
                        order.getRecipientAddress().getAddress().getTelephoneNumber(),
                        order.getRecipientAddress().getAddress().getEmail(),
                        order.getDate().toString(),
                        order.getCourier().getName(),
                        order.getStatus(),
                        order.getSenderAddress().getAddress().getName(),
                        order.getSenderAddress().getAddress().getSurname()
                ));
            }
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("date"));
        courierColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("courier"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("status"));
        senderNameColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("senderName"));
        senderSurnameColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("senderSurname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("zipCode"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("email"));
        ObservableList<UserOrderDto> observableListOrders = FXCollections.observableArrayList(userOrderDtos);
        tableView.setItems(observableListOrders);
    }

    /**
     * This method shows wiev with order's details.
     */
    @FXML
    public void openDetails(ActionEvent event) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
            UserOrder order = orderRepository.getOne(selectedId);

            Address sender = new Address(order.getSenderAddress().getAddress().getName(),
                    order.getSenderAddress().getAddress().getSurname(),
                    order.getSenderAddress().getAddress().getCity(), order.getSenderAddress().getAddress().getStreet(),
                    order.getSenderAddress().getAddress().getHouseNumber(), order.getSenderAddress().getAddress().getZipCode(),
                    order.getSenderAddress().getAddress().getTelephoneNumber(), order.getSenderAddress().getAddress().getEmail());
            Address received = new Address(order.getRecipientAddress().getAddress().getName(),
                    order.getRecipientAddress().getAddress().getSurname(),
                    order.getRecipientAddress().getAddress().getCity(), order.getRecipientAddress().getAddress().getStreet(),
                    order.getRecipientAddress().getAddress().getHouseNumber(), order.getRecipientAddress().getAddress().getZipCode(),
                    order.getRecipientAddress().getAddress().getTelephoneNumber(), order.getRecipientAddress().getAddress().getEmail());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFinalize.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent root = loader.load();
                UserOrderFinalizeController finalizeController = loader.getController();
                finalizeController.initialize(order, sender, received, order.getCourier(), order.getParcel());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            alertError("Zaznacz zamówienie dla którego chcesz wyświetlić szczegóły!");
        }
    }

    @FXML
    public void addOpinion(ActionEvent event) {
        try {
            this.orderID = tableView.getSelectionModel().getSelectedItem().getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderAddOpinion.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();
            UserOrderAddOpinionController userOrderAddOpinionController = loader.getController();
            userOrderAddOpinionController.initialize(orderID);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            alertError("Zaznacz zamówienie dla którego chcesz wystawić opinię!");
        }
    }

    private void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
            btnAddOpinion.setVisible(opinionRepository.findByUserOrder_Id(selectedId) == null);
        } catch (Exception e) {
            showDialog("Wybierz zamówienie, dla którego chcesz wykonać akcję");
        }
    }

    /**
     * This method allows to cancel order by changing its status to canceld.
     */
    @FXML
    public void cancelOrder(ActionEvent event) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
            UserOrder order = orderRepository.getOne(selectedId);
            if (order.getStatus().toString() == "WYSLANO_ZGLOSZENIE") {
                if (getLoggedUser().getPremiumPointsBalance() >= order.getPremiumPoints()) {
                    order.setStatus(ANULOWANO);
                    orderRepository.save(order);
                    User user = getLoggedUser();
                    Double account = user.getAccountBalance() + order.getPremiumPoints();
                    user.setAccountBalance(account);
                    user.setPremiumPointsBalance(user.getPremiumPointsBalance() - order.getPremiumPoints());
                    userRepository.save(user);
                    showDialog("Anulowano zamówienie");
                } else {
                    showDialog("Za mało punktów prenium aby anulowć zamówienie");
                }
            } else {
                showDialog("Nie można anulować zamówienia!");
            }
        } catch (Exception e) {
            alertError("Zaznacz zamówienie, które chcesz anulować!");
        }
    }

    @FXML
    public void openUserMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
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
