package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Address;
import serwisPaczek.repository.AddressRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserProfileEditProfileController {
    String name;
    String surname;
    String city;
    String email;
    String zip;
    String street;
    int home;
    long phone;
    private SceneManager sceneManager;
    @Autowired
    private AddressRepository addressRepository;
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
        name = TFname.getText();
        surname = TFsurname.getText();
        city = TFcity.getText();
        email = TFemail.getText();
        zip = TFzip.getText();
        street = TFstreet.getText();
        home = Integer.parseInt(TFhome.getText());
        phone = Long.parseLong(TFphone.getText());
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


                if (getLoggedUser().getAddress() != null) {

                    Address address = editUserAddress(getLoggedUser().getAddress());
                    userService.saveAddressToLoggedUser(getLoggedUser(), address);

                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.USER_MAIN);
                } else {
                    Address address = new Address(name, surname, city, street, home, zip, phone, email);
                    addressRepository.save(address);

                    userService.saveAddressToLoggedUser(getLoggedUser(), address);
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

    public Address editUserAddress(Address address) {
        address.setName(name);
        address.setSurname(surname);
        address.setStreet(street);
        address.setCity(city);
        address.setEmail(email);
        address.setZipCode(zip);
        address.setHouseNumber(home);
        address.setTelephoneNumber(phone);
        return addressRepository.save(address);
    }


    @FXML
    public void openMainUserPanel(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
