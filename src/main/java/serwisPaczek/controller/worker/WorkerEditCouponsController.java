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
import serwisPaczek.model.Coupon;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.CouponRepository;

import java.util.List;

@Controller
public class WorkerEditCouponsController {
    private SceneManager sceneManager;

    @Autowired
    CouponRepository couponRepository;


    @FXML
    private TextField addCouponName;
    @FXML
    private TextField addDiscount;
    @FXML
    private TableView<Coupon> tableView;
    @FXML
    private TableColumn<Coupon, String> idColumn;
    @FXML
    private TableColumn<Coupon, String> nameColumn;
    @FXML
    private TableColumn<Coupon, Integer> discountColumn;


    @FXML
    public void initialize(){
        fillTableView();
    }

    @FXML
    public void openMainWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    /**
     * This method is used to add coupon to the database with name and discount rate taken from textFields.
     */
    @FXML
    public void addCoupon(ActionEvent event){
        if (addCouponName.getText().equals("") || addDiscount.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Wypełnij dane", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        try {
            Coupon couponTEST = new Coupon(addCouponName.getText(),Integer.valueOf(addDiscount.getText()));
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Niepoprawne dane", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        Coupon coupon = new Coupon(addCouponName.getText(),Integer.valueOf(addDiscount.getText()));
        List<Coupon> couponList = couponRepository.findAll();
        for (Coupon couponFindByName : couponList){
            if (couponFindByName.getName().equals(coupon.getName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Kupon o danej nazwie już istnieje", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.show();
                return;
            }
        }
        if (coupon.getDiscount()<1 || coupon.getDiscount() > 99){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Niepoprawna wartość zniżki", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        
        couponList.add(coupon);
        couponRepository.saveAll(couponList);
        fillTableView();
    }

    /**
     * This method deletes coupon from the database.
     */
    @FXML
    public void deleteCoupon(ActionEvent event){
        try {
            Coupon coupon = tableView.getSelectionModel().getSelectedItem();
            couponRepository.delete(coupon);
            fillTableView();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Wybierz kupon do usunięcia", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    /**
     * This method changes name of the coupon.
     * It checks if there is already coupon named like the one which name is being changed.
     */
    @FXML
    public void changeNameEvent(TableColumn.CellEditEvent event){
        Coupon coupon = tableView.getSelectionModel().getSelectedItem();
        List<Coupon> couponList = couponRepository.findAll();
        for (Coupon couponFindByName : couponList){
            if (couponFindByName.getName().equals(event.getNewValue().toString())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Kupon o danej nazwie już istnieje", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.show();
                return;
            }
        }
        coupon.setName(event.getNewValue().toString());
        couponRepository.save(coupon);
        fillTableView();
    }

    /**
     * This method changes discount value of the coupon.
     */
    @FXML
    public void changeDiscountEvent(TableColumn.CellEditEvent event){
        Coupon coupon = tableView.getSelectionModel().getSelectedItem();
        if (Integer.valueOf(event.getNewValue().toString())<1 || Integer.valueOf(event.getNewValue().toString()) > 99){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Niepoprawna wartość zniżki", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Komunikat");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        coupon.setDiscount(Integer.valueOf(event.getNewValue().toString()));
        couponRepository.save(coupon);
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
        List<Coupon> couponList = couponRepository.findAll();
        idColumn.setCellValueFactory(new PropertyValueFactory<Coupon, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Coupon, String>("name"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<Coupon, Integer>("discount"));
        ObservableList<Coupon> observableListCoupons = FXCollections.observableArrayList(couponList);
        tableView.setItems(observableListCoupons);
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        discountColumn.setCellFactory(TextFieldTableCell.<Coupon, Integer>forTableColumn(new IntegerStringConverter()));
    }
}
