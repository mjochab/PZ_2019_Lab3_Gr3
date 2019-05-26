package serwisPaczek.controller.worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Gift;
import serwisPaczek.model.GiftOrder;
import serwisPaczek.repository.GiftOrderRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.GiftRepository;

import java.util.List;

@Controller
public class WorkerEditGiftController {
    private SceneManager sceneManager;

    @Autowired
    GiftRepository giftRepository;
    @Autowired
    GiftOrderRepository giftOrderRepository;

    @FXML
    private TextField addGiftName;
    @FXML
    private TextField addGiftPoints;
    @FXML
    private TableView<Gift> tableView;
    @FXML
    private TableColumn<Gift, String> idColumn;
    @FXML
    private TableColumn<Gift, String> nameColumn;
    @FXML
    private TableColumn<Gift, Integer> premiumPointsColumn;
    @FXML
    private TableColumn<Gift, String> statusColumn;

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
        Gift gift = new Gift(addGiftName.getText(),Integer.valueOf(addGiftPoints.getText()),"AKTYWNY");
        List<Gift> giftList = giftRepository.findAll();
        giftList.add(gift);
        giftRepository.saveAll(giftList);
        fillTableView();
    }

    @FXML
    public void deleteGift(ActionEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
        for (GiftOrder giftOrder : giftOrderList){
            if (giftOrder.getGift().getId() == gift.getId()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Ten prezent został już zamówiony i nie można usunąć go z bazy!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.show();
                return;
            }
        }
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
    public  void changeStatus(ActionEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        if (gift.getStatus().equals("AKTYWNY")) gift.setStatus("NIEAKTYWNY");
        else gift.setStatus("AKTYWNY");
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
        statusColumn.setCellValueFactory(new PropertyValueFactory<Gift, String>("status"));
        premiumPointsColumn.setCellValueFactory(new PropertyValueFactory<Gift, Integer>("premiumPoints"));
        ObservableList<Gift> observableListGifts = FXCollections.observableArrayList(giftList);
        tableView.setItems(observableListGifts);
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        premiumPointsColumn.setCellFactory(TextFieldTableCell.<Gift, Integer>forTableColumn(new IntegerStringConverter()));
    }
}
