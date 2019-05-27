package serwisPaczek.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.model.dto.UserLoginDto.setLoggedUser;

@Controller
public class AdminMainController {
    private SceneManager sceneManager;

    @FXML
    private Button btnWelcomeAdmin;
    @FXML
    private Button btnFillDataBaseWithExampleData;
    @Autowired
    private MainService mainService;
    @Autowired
    private UserRepository userRepository;

    @FXML
    public void initialize() {
        btnWelcomeAdmin.setText("Witaj " + getLoggedUser().getUsername() + "!");
        if(userRepository.findByUsername("Uzytkownik1")!=null){
            btnFillDataBaseWithExampleData.setVisible(false);
        }
    }

    /**
     * This method is used to fill the database with example data.
     */
    @FXML
    public void fillDataBaseWithExampleData() {
        mainService.fillDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Dane zostały poprawnie dodane do bazy", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    void openManageUserPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MANAGE_USERS);
    }

    @FXML
    void openManageWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MANAGE_WORKERS);
    }

    @FXML
    public void openEditCourier(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_EDIT_COURIER);
    }

    @FXML
    public void openManageParcelPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MANAGE_PARCELS);
    }

    @FXML
    public void openAddCourierPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_ADD_COURIER);
    }

    @FXML
    void openEditAboutPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_EDIT_ABOUT);
    }

    @FXML
    void logout(ActionEvent event) {
        setLoggedUser(null);
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}