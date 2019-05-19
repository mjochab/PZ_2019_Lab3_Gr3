package serwisPaczek.controller.user.profile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.controller.user.order.UserOrderAddOpinionController;
import serwisPaczek.model.UserOrder;
import serwisPaczek.model.dto.UserOrderDto;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.SceneManager.stage;

@SuppressWarnings("Duplicates")
@Controller
public class UserProfileOrderListController {
    private SceneManager sceneManager;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
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

    private ApplicationContext context;
    private Long orderID;
    @FXML
    public void initialize() {
        List<UserOrder> listOrders = orderRepository.findAll();
        List<UserOrderDto> userOrderDtos = new ArrayList<>();

        for (UserOrder order : listOrders) {
            if(order.getUser().getId() == getLoggedUser().getId()) {
                userOrderDtos.add(new UserOrderDto(order.getId(),
                        order.getRecipientAdress().getAdress().getName(),
                        order.getRecipientAdress().getAdress().getSurname(),
                        order.getRecipientAdress().getAdress().getCity(),
                        order.getRecipientAdress().getAdress().getStreet(),
                        order.getRecipientAdress().getAdress().getHouseNumber(),
                        order.getRecipientAdress().getAdress().getZipCode(),
                        order.getRecipientAdress().getAdress().getTelephoneNumber(),
                        order.getRecipientAdress().getAdress().getEmail(),
                        order.getDate().toString(),
                        order.getCourier().getName(),
                        order.getStatus(),
                        order.getSenderAdress().getAdress().getName(),
                        order.getSenderAdress().getAdress().getSurname()
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

    @FXML
    public void openDetails(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_FINALIZE);
    }

    @FXML
    public void addOpinion(ActionEvent event) {
        this.orderID = tableView.getSelectionModel().getSelectedItem().getId();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderAddOpinion.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();
            UserOrderAddOpinionController userOrderAddOpinionController = loader.getController();
            userOrderAddOpinionController.initialize(orderID);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
