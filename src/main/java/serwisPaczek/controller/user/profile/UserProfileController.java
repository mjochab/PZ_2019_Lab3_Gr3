package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

public class UserProfileController {
    private SceneManager sceneManager;

    @FXML
    private TextField TFphone;

    @FXML
    private TextField TFcity;

    @FXML
    private TextField TFhouse;

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
        sceneManager.show(SceneType.USER_MAIN);
    }

    @FXML
    public void GoToMyOpinion(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }


    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        if (UserLoginDto.getLoggedUser().getAdress() != null) {
            TFname.setText(UserLoginDto.getLoggedUser().getAdress().getName());
            TFsurname.setText(UserLoginDto.getLoggedUser().getAdress().getSurname());
            TFstreet.setText(UserLoginDto.getLoggedUser().getAdress().getStreet());
            TFphone.setText(UserLoginDto.getLoggedUser().getAdress().getTelephoneNumber().toString());
            TFemail.setText(UserLoginDto.getLoggedUser().getAdress().getEmail());
            TFhouse.setText(UserLoginDto.getLoggedUser().getAdress().getHouseNumber().toString());
            TFcity.setText(UserLoginDto.getLoggedUser().getAdress().getCity());
            TFzip.setText(UserLoginDto.getLoggedUser().getAdress().getZipCode());
        } else {
            TFname.setText("");
            TFsurname.setText("");
            TFstreet.setText("");
            TFphone.setText("");
            TFemail.setText("");
            TFhouse.setText("");
            TFcity.setText("");
            TFzip.setText("");

        }
    }
}
