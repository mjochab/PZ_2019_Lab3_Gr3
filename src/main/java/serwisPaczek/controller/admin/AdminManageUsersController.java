package serwisPaczek.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Address;
import serwisPaczek.model.Role;
import serwisPaczek.model.User;
import serwisPaczek.model.dto.UserAddressDto;
import serwisPaczek.repository.AddressRepository;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;

import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class AdminManageUsersController {
    @Autowired
    UserRepository userRepository;
    private SceneManager sceneManager;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @FXML
    private TableView<UserAddressDto> tableView;
    @FXML
    private TableColumn<UserAddressDto, String> idColumn;
    @FXML
    private TableColumn<UserAddressDto, String> userNameColumn;
    @FXML
    private TableColumn<UserAddressDto, String> nameColumn;
    @FXML
    private TableColumn<UserAddressDto, String> surnameColumn;
    @FXML
    private TableColumn<UserAddressDto, String> cityColumn;
    @FXML
    private TableColumn<UserAddressDto, String> streetColumn;
    @FXML
    private TableColumn<UserAddressDto, String> houseNumberColumn;
    @FXML
    private TableColumn<UserAddressDto, String> zipCodeColumn;
    @FXML
    private TableColumn<UserAddressDto, String> telephoneNumberColumn;
    @FXML
    private TableColumn<UserAddressDto, String> emailColumn;
    @FXML
    private TextField TFusername;

    /**
     * This method is used to show all existing users and their information in the list.
     */
    @FXML
    public void initialize() {
        List<User> listUsers = userRepository.findAll();
        List<UserAddressDto> userAddressDtos = new ArrayList<>();

        for (User user : listUsers) {
            Address address = addressRepository.findByUser(user);
            if (address != null) {
                userAddressDtos.add(new UserAddressDto(user.getId(),
                        user.getUsername(),
                        address.getName(),
                        address.getSurname(),
                        address.getCity(),
                        address.getStreet(),
                        address.getHouseNumber(),
                        address.getZipCode(),
                        address.getTelephoneNumber(),
                        address.getEmail()
                ));
            } else
                userAddressDtos.add(new UserAddressDto(user.getId(),
                        user.getUsername(),
                        "",
                        "",
                        "",
                        "",
                        0,
                        "",
                        0L,
                        ""
                ));
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("zipCode"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<UserAddressDto, String>("email"));
        ObservableList<UserAddressDto> observableListUsers = FXCollections.observableArrayList(userAddressDtos);
        tableView.setItems(observableListUsers);
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
        } catch (Exception e) {
            showDialog("Wybierz użytkownika, dla którego chcesz wykonać akcję");
        }
    }

    @FXML
    public void editStatus(ActionEvent event) {
        try {
            Long selectedId = tableView.getSelectionModel().getSelectedItem().getId();
            User user = userRepository.getOne(selectedId);
            Role role = roleRepository.findByRoleName("WORKER_ROLE");
            user.setRole(role);
            userRepository.save(user);
            showDialog("Dodano pracownika");
        } catch (Exception e) {
            showDialog("Wybierz użytkownika, dla którego chcesz wykonać akcję");
        }
    }

    @FXML
    public void openAdminMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}