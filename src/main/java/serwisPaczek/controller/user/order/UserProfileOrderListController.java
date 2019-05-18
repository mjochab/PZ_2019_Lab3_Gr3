package serwisPaczek.controller.user.order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.model.UserOrder;
import serwisPaczek.model.dto.UserAdressDto;
import serwisPaczek.model.dto.UserOrderDto;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.repository.CourierRepository;
import serwisPaczek.repository.RecipientAdressRepository;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserProfileOrderListController {
    private SceneManager sceneManager;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private AdressRepository adressRepository;

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
    private TableColumn<UserOrderDto, String> surnameColumn;
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
                        order.getStatus()
                ));
            }

            }

        idColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("date"));
        courierColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("courier"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<UserOrderDto, String>("status"));
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
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void OpenFinalize(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_FINALIZE);
    }

    @FXML
    public void AddOpinion(ActionEvent event) {
        sceneManager.show(SceneType.USER_ORDER_ADD_OPINION);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
