package serwisPaczek.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.repository.GiftRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderGiftController {
    private SceneManager sceneManager;

    @Autowired
    GiftRepository giftRepository;

    @FXML
    private TableView<Gift> tableView;
    @FXML
    private TableColumn<Gift, String> idColumn;
    @FXML
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, String> premiumPointsColumn;

    @FXML
    public void initialize() {
        List<Gift> listGifts = giftRepository.findAll();
        List<Gift> gifts = new ArrayList<>();
        for(Gift gift : listGifts) {
        }


        idColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("premiumPoints"));

        ObservableList<Gift> observableListUsers = FXCollections.observableArrayList(gifts);
        tableView.setItems(observableListUsers);
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
