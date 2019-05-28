package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Address;

import serwisPaczek.model.User;
import serwisPaczek.repository.AddressRepository;

import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserProfileEditProfileController {
    private SceneManager sceneManager;
    @Autowired
    private AddressRepository adressRepository;
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

    /**
     * This method fills textfields on page with data.
     * If user don't have assigned adress relevant textfields are left empty.
     */
    @FXML
    public void initialize() {
        if (getLoggedUser().getAddress() != null) {
            TFname.setText(getLoggedUser().getAddress().getName());
            TFsurname.setText(getLoggedUser().getAddress().getSurname());
            TFstreet.setText(getLoggedUser().getAddress().getStreet());
            TFphone.setText(getLoggedUser().getAddress().getTelephoneNumber().toString());
            TFemail.setText(getLoggedUser().getAddress().getEmail());
            TFhome.setText(getLoggedUser().getAddress().getHouseNumber().toString());
            TFcity.setText(getLoggedUser().getAddress().getCity());
            TFzip.setText(getLoggedUser().getAddress().getZipCode());
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
    }

    /**
     * This method is used to edit user data and handle incorrect input data.
     */
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
                String name = TFname.getText();
                String surname = TFsurname.getText();
                String city = TFcity.getText();
                String email = TFemail.getText();
                String zip = TFzip.getText();
                String street = TFstreet.getText();
                int home = Integer.parseInt(TFhome.getText());
                long phone = Long.parseLong(TFphone.getText());

                if (getLoggedUser().getAddress() != null) {
                    Address adress = getLoggedUser().getAddress();
                    adress.setName(name);
                    adress.setSurname(surname);
                    adress.setStreet(street);
                    adress.setCity(city);
                    adress.setEmail(email);
                    adress.setZipCode(zip);
                    adress.setHouseNumber(home);
                    adress.setTelephoneNumber(phone);
                    adressRepository.save(adress);
                    User user = getLoggedUser();
                    user.setAddress(adress);
                    userRepository.save(user);
                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.USER_MAIN);
                } else {
                    Address adress = new Address(name, surname, city, street, home, zip, phone, email);
                    adressRepository.save(adress);
                    User user = getLoggedUser();
                    user.setAddress(adress);
                    userRepository.save(user);
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

    @FXML
    public void openMainUserPanel(ActionEvent event) { sceneManager.show(SceneType.USER_MAIN); }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) { this.sceneManager = sceneManager; }
}