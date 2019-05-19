package serwisPaczek.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import javafx.scene.control.TextArea;
import serwisPaczek.model.About;
import java.util.List;
import serwisPaczek.repository.AboutRepository;

@Controller
public class AdminEditAboutController {
    private SceneManager sceneManager;

    @FXML
    private TextArea textArea;

    @Autowired
    AboutRepository aboutRepository;

    @FXML
    public void initialize(){
        List<About> listAbout = aboutRepository.findAll();
        textArea.setText(listAbout.get(0).getContent());
    }

    @FXML
    public void acceptChanges (ActionEvent event){
        List<About> listAbout = aboutRepository.findAll();
        listAbout.get(0).setContent(textArea.getText());
        aboutRepository.saveAll(listAbout);
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
