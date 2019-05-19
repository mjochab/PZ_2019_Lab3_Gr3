package serwisPaczek.controller.worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.GiftRepository;
import java.util.List;

@Controller
public class WorkerEditGiftController {
    private SceneManager sceneManager;

    @Autowired
    GiftRepository giftRepository;

    @FXML
    private TextField addGiftName;
    @FXML
    private TextField addGiftPoints;
    @FXML
    private TextField editGiftName;
    @FXML
    private TextField editGiftPoints;
    @FXML
    private TableView<Gift> tableView;
    @FXML
    private TableColumn<Gift, String> idColumn;
    @FXML
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, Integer> premiumPointsColumn;

    @FXML
    public void initialize(){
        fillTableView();
    }

    @FXML
    public void openMainWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    @FXML
    public void addGift(ActionEvent event){
        Gift gift = new Gift(addGiftName.getText(),Integer.valueOf(addGiftPoints.getText()));
        List<Gift> giftList = giftRepository.findAll();
        giftList.add(gift);
        giftRepository.saveAll(giftList);
        fillTableView();
    }

    @FXML
    public void deleteGift(ActionEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        giftRepository.delete(gift);
        fillTableView();
    }

    @FXML
    public void changeNameEvent(TableColumn.CellEditEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        gift.setName(event.getNewValue().toString());
        giftRepository.save(gift);
        fillTableView();
    }
    @FXML
    public void changePremiumPointsEvent(TableColumn.CellEditEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        gift.setPremiumPoints(Integer.valueOf(event.getNewValue().toString()));
        giftRepository.save(gift);
        fillTableView();
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    void fillTableView(){
        List<Gift> giftList = giftRepository.findAll();
        idColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("name"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, Integer>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        premiumPointsColumn.setCellFactory(TextFieldTableCell.<Gift, Integer>forTableColumn(new IntegerStringConverter()));
    }
}
