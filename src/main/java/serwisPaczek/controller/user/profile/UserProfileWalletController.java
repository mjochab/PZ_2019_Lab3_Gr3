package serwisPaczek.controller.user.profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.User;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;

@Controller
public class UserProfileWalletController {
    private SceneManager sceneManager;
    private User user;
    @Autowired
    private UserService userService;
    @FXML
    private TextField accountBalanceTextField;
    @FXML
    private TextField depositTextField;
    @FXML
    private TextField withdrawTextField;

    public void initialize() {
        user = getLoggedUser();
        accountBalanceTextField.setText(user.getAccountBalance() + " PLN");
    }

    /**
     * This method is used to deposit funds by user and handle incorrect input data.
     */
    @FXML
    public void depositFunds(ActionEvent event) {
        try {
            if (depositTextField.getText().matches("\\d+(\\.\\d{1,2})?")) {
                double depositValue = Double.valueOf(depositTextField.getText());
                if (depositValue > 0 && user.getAccountBalance() + depositValue <= 1000000) {
                    userService.depositFunds(user, depositValue);
                    showDialog("Pomyślnie wpłacono środki.");
                    sceneManager.show(SceneType.USER_PROFILE_WALLET);
                } else {
                    throw new NumberFormatException();
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog("Kwota wpłaty musi być większa od zera." +
                    "\nMaksymalnie dwie cyfry po przecinku." +
                    "\nPrzecinek zapisuj jako kropkę." +
                    "\nMaksymalny stan konta: 1 mln PLN.");
        }
    }

    /**
     * This method is used to withdraw funds by user and handle incorrect input data.
     */
    @FXML
    public void withdrawFunds(ActionEvent event) {
        try {
            if (withdrawTextField.getText().matches("\\d+(\\.\\d{1,2})?")
            ) {
                double withdrawValue = Double.valueOf(withdrawTextField.getText());
                if (withdrawValue > 0 && user.getAccountBalance() >= withdrawValue) {
                    userService.withdrawFunds(user, withdrawValue);
                    showDialog("Pomyślnie wypłacono środki.");
                    sceneManager.show(SceneType.USER_PROFILE_WALLET);
                } else {
                    throw new NumberFormatException();
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog("Kwota wypłaty nie może być większa niż stan twojego konta." +
                    "\nMaksymalnie dwie cyfry po przecinku." +
                    "\nPrzecinek zapisuj jako kropkę.");
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
