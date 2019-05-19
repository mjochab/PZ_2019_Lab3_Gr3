package serwisPaczek.controller.worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    public void openMainWorkerPanel(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    @FXML
    public void addCoupon(ActionEvent event){
        Coupon coupon = new Coupon(addCouponName.getText(),Integer.valueOf(addDiscount.getText()));
        List<Coupon> couponList = couponRepository.findAll();
        couponList.add(coupon);
        couponRepository.saveAll(couponList);
        fillTableView();
    }

    @FXML
    public void deleteGift(ActionEvent event){
    }

    @FXML
    public void changeNameEvent(TableColumn.CellEditEvent event){
    }
    @FXML
    public void changeDiscountEvent(TableColumn.CellEditEvent event){
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    void fillTableView(){
    }
}
