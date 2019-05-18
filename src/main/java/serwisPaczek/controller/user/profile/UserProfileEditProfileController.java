package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.repository.UserRepository;
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

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
        }

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
    }

    @FXML
    public void EditUser(ActionEvent event) {
        try {
            if (TFname.getText().length() > 0
                    && TFsurname.getText().length() > 0
                    && TFcity.getText().length() > 0
                    && TFemail.getText().length() > 0
                    && TFzip.getText().length() > 0
                    && TFhome.getText().length() > 0
                    && this.TFhome.getText().matches("\\d+")
                    && TFphone.getText().length() > 0
                    && this.TFphone.getText().matches("\\d+")) {
                String name = TFname.getText();
                String surname = TFsurname.getText();
                String city = TFcity.getText();
                String email = TFemail.getText();
                String zip = TFzip.getText();
                String street = TFstreet.getText();
                int home = Integer.parseInt(TFhome.getText());
                long phone = Long.parseLong(TFphone.getText());

                if (getLoggedUser().getAdress() != null) {

                    Adress adress = getLoggedUser().getAdress();
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
                    user.setAdress(adress);
                    userRepository.save(user);
                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.MAIN);

                }

                else {
                    Adress adress = new Adress(name, surname, city, street, home, zip, phone, email);
                    adressRepository.save(adress);
                    User user = getLoggedUser();
                    user.setAdress(adress);
                    userRepository.save(user);
                    showDialog("Poprawna edycja profilu.");
                    sceneManager.show(SceneType.MAIN);


                }}


            else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog("Uzupełnij wszystkie pola poprawnymi danymi.");

        }

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
