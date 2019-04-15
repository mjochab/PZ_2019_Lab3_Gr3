package serwisPaczek.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.User;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;
import java.util.Observable;

@Controller
public class UsersListController {
    private SceneManager sceneManager;

    @Autowired
    UserRepository userRepository;

    //Configure the table
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> surnameColumn;
    @FXML
    private TableColumn<User, String> cityColumn;
    @FXML
    private TableColumn<User, String> streetColumn;
    @FXML
    private TableColumn<User, String> houseNumberColumn;
    @FXML
    private TableColumn<User, String> zipCodeColumn;
    @FXML
    private TableColumn<User, String> telephoneNumberColumn;
    @FXML
    private TableColumn<User, String> emailColumn;


    public void fillTest(ActionEvent event) {
        List<User> listUsers = userRepository.findAll();

        for (User user : listUsers) {
            System.out.println(
                    user.getId() + " " +
                            user.getUsername() + " " +
                            user.getAdress().getName() + " " +
                            user.getAdress().getSurname() + " " +
                            user.getAdress().getCity() + " " +
                            user.getAdress().getStreet() + " " +
                            user.getAdress().getHouseNumber() + " " +
                            user.getAdress().getZipCode() + " " +
                            user.getAdress().getTelephoneNumber() + " " +
                            user.getAdress().getEmail()
            );
        }
    }

    @FXML
    public void initialize() {
        //set up the columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<User, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<User, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("zipCode"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        //load data
        List<User> listUsers = userRepository.findAll();
        ObservableList<User> observableListUsers = FXCollections.observableArrayList(listUsers);
        tableView.setItems(observableListUsers);
    }

    @FXML
    public void BackToAdmin(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MENU);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
