package serwisPaczek.controller.worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.model.dto.GiftOrderDto;
import serwisPaczek.repository.*;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkerManageGiftOrdersController {
    @Autowired
    GiftRepository giftRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipientAddressRepository recipientAddressRepository;
    @Autowired
    GiftOrderRepository giftOrderRepository;
    @Autowired
    AddressRepository addressRepository;
    private SceneManager sceneManager;
    @FXML
    private TextField idTextField;
    @FXML
    private ComboBox<Status> statusComboBox;
    @FXML
    private TableView<GiftOrderDto> tableView;
    @FXML
    private TableColumn<GiftOrderDto, String> idColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> giftNameColumn;
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
    private TableColumn<GiftOrderDto, String> dateColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> emailColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> telephoneColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> senderNameColumn;
    @FXML
    private TableColumn<GiftOrderDto, String> statusColumn;

    @FXML
    public void initialize() {
        fillTableView(0);
        statusComboBox.getItems().setAll(Status.values());
        statusComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void openMainWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * This method fills tableView with data fro database
     * It creates objects address, Recipientaddress and UserOrder and combines data from them into new class GiftOrderDto.
     */
    void fillTableView(int id) {
        List<GiftOrderDto> giftOrderDtoList = new ArrayList<>();
        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        List<Gift> giftList = giftRepository.findAll();
        List<User> userList = userRepository.findAll();
        List<RecipientAddress> recipientaddressList = recipientAddressRepository.findAll();
        List<Address> addressList = addressRepository.findAll();
        for (GiftOrder giftOrder : giftOrderList) {
            String giftName = "";
            String senderName = "";
            String name = "";
            String surname = "";
            String city = "";
            String street = "";
            String houseNumber = "";
            String zipCode = "";
            String telephoneNumber = "";
            String email = "";
            for (Gift gift : giftList) {
                if (giftOrder.getGift().getId() == gift.getId()) {
                    giftName = gift.getName();
                }
            }
            for (User user : userList) {
                if (giftOrder.getUser().getId() == user.getId()) {
                    senderName = user.getUsername();
                }
            }
            for (RecipientAddress recipientAddress : recipientaddressList) {
                if (giftOrder.getRecipientAddress().getId() == recipientAddress.getId()) {
                    name = recipientAddress.getAddress().getName();
                    surname = recipientAddress.getAddress().getSurname();
                    city = recipientAddress.getAddress().getCity();
                    street = recipientAddress.getAddress().getStreet();
                    houseNumber = String.valueOf(recipientAddress.getAddress().getHouseNumber());
                    zipCode = String.valueOf(recipientAddress.getAddress().getZipCode());
                    telephoneNumber = String.valueOf(recipientAddress.getAddress().getTelephoneNumber());
                    email = recipientAddress.getAddress().getEmail();
                }
            }
            GiftOrderDto giftOrderDto = new GiftOrderDto(giftOrder.getId(), giftName, name, surname, city, street, Integer.valueOf(houseNumber), zipCode, Long.valueOf(telephoneNumber), email, String.valueOf(giftOrder.getDate()), senderName, giftOrder.getStatus().name());
            giftOrderDtoList.add(giftOrderDto);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("id"));
        giftNameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("giftName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("zipCode"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("date"));
        senderNameColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("senderName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<GiftOrderDto, String>("status"));

        if (id != 0) {
            for (GiftOrderDto giftOrderDto : giftOrderDtoList) {
                if (giftOrderDto.getId() == id) {
                    List<GiftOrderDto> giftOrderDtoItem = new ArrayList<>();
                    giftOrderDtoItem.add(giftOrderDto);
                    ObservableList<GiftOrderDto> observableListGiftOrderDtos = FXCollections.observableArrayList(giftOrderDtoItem);
                    tableView.setItems(observableListGiftOrderDtos);
                    break;
                }
            }
        } else {
            ObservableList<GiftOrderDto> observableListGiftOrderDtos = FXCollections.observableArrayList(giftOrderDtoList);
            tableView.setItems(observableListGiftOrderDtos);
        }
    }

    /**
     * This method changes status of the gift order.
     */
    @FXML
    public void changeStatus(ActionEvent event) {
        GiftOrderDto giftOrderDto = tableView.getSelectionModel().getSelectedItem();
        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        for (GiftOrder giftOrder : giftOrderList) {
            if (giftOrder.getId() == giftOrderDto.getId()) {
                giftOrder.setStatus(statusComboBox.getValue());
                giftOrderRepository.save(giftOrder);
                break;
            }
        }
        fillTableView(0);
    }

    /**
     * This method parses parameter from textField to the fillTableView method.
     */
    @FXML
    public void searchGiftOrder(ActionEvent event) {
        if (idTextField.getText().equals("")) {
            fillTableView(0);
        } else {
            fillTableView(Integer.valueOf(idTextField.getText()));
        }
    }
}
