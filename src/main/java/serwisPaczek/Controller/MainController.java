package main.java.serwisPaczek.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import main.java.serwisPaczek.utils.DialogsUtils;
import main.java.serwisPaczek.utils.FxmlUtils;

import java.util.Optional;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {

    private static final String LOGIN_FXML = "/fxml/Login.fxml";
    private static final String HOME_FXML = "/fxml/MainPage.fxml";
    @FXML
    private BorderPane borderPane;
    @FXML
    private OrderController orderController;

    public void setCenter(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }


    public void closeApplication() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void openAbout() {
        DialogsUtils.dialogAboutApplication();
    }

    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }


    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }


    public void logout(ActionEvent actionEvent) {
    }

    public void openRegister(ActionEvent actionEvent) {
    }

    public void openLogin() {
        borderPane.setCenter(FxmlUtils.fxmlLoader(LOGIN_FXML));
    }

    public void OpenHomePage() {
        borderPane.setCenter(FxmlUtils.fxmlLoader(HOME_FXML));
    }
}
