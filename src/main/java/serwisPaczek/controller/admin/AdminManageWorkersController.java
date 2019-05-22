package serwisPaczek.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.User;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdminManageWorkersController {
    @Autowired
    UserRepository userRepository;
    private SceneManager sceneManager;
    @FXML
    private ListView<String> workerList;
    @FXML
    private ComboBox<String> workerStatusComboBox;
    @FXML
    private TextField searchTextField;

    public void initialize() {
        fillListWithAllWorkers();
        workerStatusComboBox.getItems().setAll("Activate", "Deactivate");
        workerStatusComboBox.getSelectionModel().selectFirst();
    }


    private void fillListWithWorkers(User worker) {
        workerList.getItems().add("ID: " + worker.getId().toString() + "  |  " + worker.getUsername() + "  |  Konto " +
                (worker.getActive() ? "aktywne" : "zablokowane"));
    }

    private void fillListWithAllWorkers() {
        List<User> workers = userRepository.findAllByRole_RoleName("WORKER_ROLE");
        for (User worker : workers) {
            fillListWithWorkers(worker);
        }
    }

    @FXML
    public void orderSearch(ActionEvent event) {
        try {
            User worker = userRepository.findByIdAndRole_RoleName(
                    Long.valueOf(searchTextField.getText()), "WORKER_ROLE");
            workerList.getItems().clear();
            fillListWithWorkers(worker);
        } catch (Exception e) {
            alertError("Nie znaleziono pracownika o takim id");
        }
    }

    @FXML
    public void workerChangeStatus(ActionEvent event) {
        try {
            // Getting id from selected item in the list
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(workerList.getSelectionModel().getSelectedItem());
            Long id = null;
            if (m.find()) {
                id = Long.valueOf(m.group(0));
            }

            User worker = userRepository.findByIdAndRole_RoleName(
                    id, "WORKER_ROLE");
            worker.setActive((workerStatusComboBox.getValue().equals("Activate")));
            userRepository.save(worker);
            workerList.getItems().clear();
            fillListWithWorkers(worker);
        } catch (Exception e) {
            alertError("Zaznacz pracownika!");
        }
    }

    private void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.show();
    }

    @FXML
    public void resetSearch(ActionEvent event) {
        workerList.getItems().clear();
        fillListWithAllWorkers();
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
