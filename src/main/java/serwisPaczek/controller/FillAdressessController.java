package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

@Controller
public class FillAdressessController {
    private SceneManager sceneManager;
//
    @FXML
    private CheckBox fillAdressCheckbox;

    @FXML
    private TextField TFnr;

    @FXML
    private TextField TFspot;

    @FXML
    private TextField TFhouseNumber;

    @FXML
    private TextField TFname;

    @FXML
    private TextField TFstreet;

    @FXML
    private TextField TFemail;

    @FXML
    private TextField TFzipCode;

    @FXML
    private TextField TFsurname;


    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @FXML
    public void OpenFinalize(ActionEvent event) {
        sceneManager.show(SceneType.FINALIZE);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        fillAdressCheckbox.setVisible(false);
        if(UserLoginDto.getLoggedUser().getAdress()!=null){
            fillAdressCheckbox.setVisible(true);
        }
    }

    @FXML
    public void fillAdress(ActionEvent event){
if(fillAdressCheckbox.isSelected()){
TFname.setText(UserLoginDto.getLoggedUser().getAdress().getName());
TFsurname.setText(UserLoginDto.getLoggedUser().getAdress().getSurname());
    TFstreet.setText(UserLoginDto.getLoggedUser().getAdress().getStreet());
    TFnr.setText(UserLoginDto.getLoggedUser().getAdress().getTelephoneNumber().toString());
    TFemail.setText(UserLoginDto.getLoggedUser().getAdress().getEmail());
    TFhouseNumber.setText(UserLoginDto.getLoggedUser().getAdress().getHouseNumber().toString());
    TFspot.setText(UserLoginDto.getLoggedUser().getAdress().getCity());
    TFzipCode.setText(UserLoginDto.getLoggedUser().getAdress().getZipCode());
}
else {
    TFname.setText("");
    TFsurname.setText("");
    TFstreet.setText("");
    TFnr.setText("");
    TFemail.setText("");
    TFhouseNumber.setText("");
    TFspot.setText("");
    TFzipCode.setText("");
}
    }
}
