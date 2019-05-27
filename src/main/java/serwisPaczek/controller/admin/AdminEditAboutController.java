package serwisPaczek.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.About;
import serwisPaczek.repository.AboutRepository;
import serwisPaczek.service.MainService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;


@Controller
public class AdminEditAboutController {
    @Autowired
    AboutRepository aboutRepository;
    private SceneManager sceneManager;
    @FXML
    private TextArea textArea;
    @Autowired
    private MainService mainService;

    @FXML
    public void initialize() {
        List<About> listAbout = aboutRepository.findAll();
        textArea.setText(listAbout.get(0).getContent());
    }

    /**
     * This method takes text from the textArea and sends it to the database.
     */
    @FXML
    public void acceptChanges(ActionEvent event) {
        List<About> listAbout = aboutRepository.findAll();
        listAbout.get(0).setContent(textArea.getText());
        aboutRepository.saveAll(listAbout);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Zmiany zostały zapisane", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    public void openAdminMainPanel(ActionEvent event) {
        sceneManager.show(SceneType.ADMIN_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
