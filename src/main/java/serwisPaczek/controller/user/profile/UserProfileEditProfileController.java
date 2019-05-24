package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserProfileEditProfileController {
    private SceneManager sceneManager;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @FXML
    private TextField TFphone;
    @FXML
    private TextField TFcity;
    @FXML
    private TextField TFhome;
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFstreet;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFzip;
    @FXML
    private TextField TFsurname;
    String name;
    String surname;
    String city;
    String email;
    String zip;
    String street;
    int home;
    long phone;
    @FXML
    public void initialize() {
        if (getLoggedUser().getAdress() != null) {
            TFname.setText(getLoggedUser().getAdress().getName());
            TFsurname.setText(getLoggedUser().getAdress().getSurname());
            TFstreet.setText(getLoggedUser().getAdress().getStreet());
            TFphone.setText(getLoggedUser().getAdress().getTelephoneNumber().toString());
            TFemail.setText(getLoggedUser().getAdress().getEmail());
            TFhome.setText(getLoggedUser().getAdress().getHouseNumber().toString());
            TFcity.setText(getLoggedUser().getAdress().getCity());
            TFzip.setText(getLoggedUser().getAdress().getZipCode());
        } else {
            TFname.setText("");
            TFsurname.setText("");
            TFstreet.setText("");
            TFphone.setText("");
            TFemail.setText("");
            TFhome.setText("");
            TFcity.setText("");
            TFzip.setText("");
        }
        name = TFname.getText();
        surname = TFsurname.getText();
        city = TFcity.getText();
        email = TFemail.getText();
        zip = TFzip.getText();
        street = TFstreet.getText();
        home = Integer.parseInt(TFhome.getText());
        phone = Long.parseLong(TFphone.getText());
    }

    @FXML
    public void EditUser(ActionEvent event) {
        try {
            if (TFname.getText().length() > 2
                    && TFname.getText().matches("[a-zA-Z]+")
                    && TFsurname.getText().length() > 2 && TFsurname.getText().matches("[a-zA-Z]+")
                    && TFcity.getText().length() > 2 && TFcity.getText().matches("[a-zA-Z]+")
                    && TFemail.getText().matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")
                    && TFzip.getText().matches("[0-9]{2}+-[0-9]{3}")
                    && TFhome.getText().length() > 0 && TFhome.getText().matches("\\d+")
                    && TFphone.getText().length() == 9 && TFphone.getText().matches("\\d+")) {


                if (getLoggedUser().getAdress() != null) {

                    Adress adress = editUserAddress(getLoggedUser().getAdress());
                    userService.saveAddressToLoggedUser(getLoggedUser(),adress);

                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.USER_MAIN);
                } else {
                    Adress adress = new Adress(name, surname, city, street, home, zip, phone, email);
                    adressRepository.save(adress);

                    userService.saveAddressToLoggedUser(getLoggedUser(),adress);
                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.USER_MAIN);
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog("Uzupełnij wszystkie pola poprawnymi danymi.");
        }
    }

    public Adress editUserAddress(Adress adress){
        adress.setName(name);
        adress.setSurname(surname);
        adress.setStreet(street);
        adress.setCity(city);
        adress.setEmail(email);
        adress.setZipCode(zip);
        adress.setHouseNumber(home);
        adress.setTelephoneNumber(phone);
        return adressRepository.save(adress);
    }


    @FXML
    public void openMainUserPanel(ActionEvent event) { sceneManager.show(SceneType.USER_MAIN); }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) { this.sceneManager = sceneManager; }
}
