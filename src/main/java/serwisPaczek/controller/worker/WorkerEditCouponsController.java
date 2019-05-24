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

    @FXML
    public void addCoupon(ActionEvent event){
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
        couponList.add(coupon);
        couponRepository.saveAll(couponList);
        fillTableView();
    }

    @FXML
    public void deleteCoupon(ActionEvent event){
        Coupon coupon = tableView.getSelectionModel().getSelectedItem();
        couponRepository.delete(coupon);
        fillTableView();
    }

    @FXML
    public void changeNameEvent(TableColumn.CellEditEvent event){
        Coupon coupon = tableView.getSelectionModel().getSelectedItem();
        coupon.setName(event.getNewValue().toString());
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
        couponRepository.save(coupon);
        fillTableView();
    }
    @FXML
    public void changeDiscountEvent(TableColumn.CellEditEvent event){
        Coupon coupon = tableView.getSelectionModel().getSelectedItem();
        coupon.setDiscount(Integer.valueOf(event.getNewValue().toString()));
        couponRepository.save(coupon);
        fillTableView();
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
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
