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

    /**
     * This method is used to add gift to the database with name and discount rate taken from textFields.
     */
    @FXML
    public void addGift(ActionEvent event){
        if (addGiftName.getText().equals("") || addGiftPoints.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Wypełnij dane!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        try {
            Gift giftTEST = new Gift(addGiftName.getText(),Integer.valueOf(addGiftPoints.getText()),"AKTYWNY");
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Niepoprawne dane!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        Gift gift = new Gift(addGiftName.getText(),Integer.valueOf(addGiftPoints.getText()),"AKTYWNY");
        if (gift.getPremiumPoints()<1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Niepoprawna wartość punktów premium!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        List<Gift> giftList = giftRepository.findAll();
        giftList.add(gift);
        giftRepository.saveAll(giftList);
        fillTableView();
    }

    /**
     * This method deletes gift from the database.
     */
    @FXML
    public void deleteGift(ActionEvent event){
        try {
            Gift giftTEST = tableView.getSelectionModel().getSelectedItem();
            Gift gift = tableView.getSelectionModel().getSelectedItem();
            List<GiftOrder> giftOrderList = giftOrderRepository.findAll();
            for (GiftOrder giftOrder : giftOrderList) {
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
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Wybierz prezent do usunięcia!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    /**
     * This method changes name of the gift.
     */
    @FXML
    public void changeNameEvent(TableColumn.CellEditEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        gift.setName(event.getNewValue().toString());
        giftRepository.save(gift);
        fillTableView();
    }

    /**
     * This method changes status of the gift (active/unactive).
     */
    @FXML
    public  void changeStatus(ActionEvent event){
        try {
            Gift gift = tableView.getSelectionModel().getSelectedItem();
            if (gift.getStatus().equals("AKTYWNY")) gift.setStatus("NIEAKTYWNY");
            else gift.setStatus("AKTYWNY");
            giftRepository.save(gift);
            fillTableView();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Wybierz prezent z listy!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
        }

    }

    /**
     * This method changes premiumPoints value of the gift.
     */
    @FXML
    public void changePremiumPointsEvent(TableColumn.CellEditEvent event){
        Gift gift = tableView.getSelectionModel().getSelectedItem();
        if (Integer.valueOf(event.getNewValue().toString())<0) {
            fillTableView();
            return;
        }
        gift.setPremiumPoints(Integer.valueOf(event.getNewValue().toString()));
        giftRepository.save(gift);
        fillTableView();
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * This method fills tableView with data from the database.
     */
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
