package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.utils.DialogsUtils.showDialog;


@Controller
public class ChooseCourierController {

    private SceneManager sceneManager;

    @FXML
    public void OpenFillAdressessForm(ActionEvent actionEvent) {
        if(UserLoginDto.getLoggedUser()!=null){
        sceneManager.show(SceneType.USER_ORDER_FILL_ADDRESSES);
    }
        else {
            showDialog("Musisz być zalogowany by dokonać zamówienia");
        }
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
