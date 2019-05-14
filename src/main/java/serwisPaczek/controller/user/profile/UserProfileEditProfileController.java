package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.repository.AdressRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserProfileEditProfileController {
    private SceneManager sceneManager;

    @Autowired
    private AdressRepository adressRepository;

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
    public void BackToUserProfile(ActionEvent event) {
        sceneManager.show(SceneType.MAIN_USER);
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

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
