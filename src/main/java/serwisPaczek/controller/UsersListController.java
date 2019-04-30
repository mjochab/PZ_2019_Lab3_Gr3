package serwisPaczek.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.model.dto.UserAdressDto;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Controller
public class UsersListController {
    private SceneManager sceneManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AdressRepository adressRepository;

    //Configure the table
    @FXML
    private TableView<UserAdressDto> tableView;
    @FXML
    private TableColumn<UserAdressDto, String> idColumn;
    @FXML
    private TableColumn<UserAdressDto, String> userNameColumn;
    @FXML
    private TableColumn<UserAdressDto, String> nameColumn;
    @FXML
    private TableColumn<UserAdressDto, String> surnameColumn;
    @FXML
    private TableColumn<UserAdressDto, String> cityColumn;
    @FXML
    private TableColumn<UserAdressDto, String> streetColumn;
    @FXML
    private TableColumn<UserAdressDto, String> houseNumberColumn;
    @FXML
    private TableColumn<UserAdressDto, String> zipCodeColumn;
    @FXML
    private TableColumn<UserAdressDto, String> telephoneNumberColumn;
    @FXML
    private TableColumn<UserAdressDto, String> emailColumn;


    @FXML
    public void initialize() {
        List<User> listUsers = userRepository.findAll();
        List<UserAdressDto> userAdressDtos = new ArrayList<>();
        for(User user : listUsers){
            Adress adress = adressRepository.findByUser(user);
            if (adress != null) {
                userAdressDtos.add(new UserAdressDto(user.getId(),
                        user.getUsername(),
                        adress.getName(),
                        adress.getSurname(),
                        adress.getCity(),
                        adress.getStreet(),
                        adress.getHouseNumber(),
                        adress.getZipCode(),
                        adress.getTelephoneNumber(),
                        adress.getEmail()
                ));}
            else
                userAdressDtos.add(new UserAdressDto(user.getId(),
                        user.getUsername(),
                        "",
                        "",
                        "",
                        "",
                        0,
                        "",
                        0L,
                        ""
                ));}

        idColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("houseNumber"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("zipCode"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<UserAdressDto, String>("email"));

        ObservableList<UserAdressDto> observableListUsers = FXCollections.observableArrayList(userAdressDtos);
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

