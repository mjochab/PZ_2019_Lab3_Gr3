package serwisPaczek.controller.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Status;
import serwisPaczek.model.UserOrder;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.List;

@Controller
public class WorkerManageParcelsController {
    @Autowired
    OrderRepository orderRepository;
    private SceneManager sceneManager;
    @FXML
    private ListView<String> workerOrdersList;
    @FXML
    private ComboBox<Status> statusComboBox;
    @FXML
    private Spinner<Integer> ordersSearchField;

    private void fillListWithOrders(UserOrder order) {
        workerOrdersList.getItems().add(order.getId().toString() + "  |  " +
                order.getUser().getUsername() + "  " +
                order.getCourier().getName() + "  " +
                order.getPrice() + " zł  " +
                order.getStatus().name() + "    " +
                order.getDate().toString());
    }

    private void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                message, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.show();
    }

    public void initialize() {

        List<UserOrder> orders = orderRepository.findAll();

        for (UserOrder order : orders) {
            fillListWithOrders(order);
        }
        statusComboBox.getItems().setAll(Status.values());
        statusComboBox.getSelectionModel().selectFirst();

        // Value factory for spinner
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        ordersSearchField.setValueFactory(valueFactory);
    }

    @FXML
    public void orderSearch(ActionEvent event) {
        try {
            UserOrder order = orderRepository.getOne(ordersSearchField.getValue().longValue());
            workerOrdersList.getItems().clear();
            fillListWithOrders(order);
        } catch (Exception e) {
            alertError("Nie znaleziono zamówienia o takim id");
        }
    }

    @FXML
    public void orderChangeStatus(ActionEvent event) {
//        String selectedItem = workerOrdersList.getSelectionModel().getSelectedItem();


        try {
            UserOrder order = orderRepository.getOne(ordersSearchField.getValue().longValue());
            order.setStatus(statusComboBox.getValue());
            orderRepository.save(order);
            workerOrdersList.getItems().clear();
            fillListWithOrders(order);
        } catch (Exception e) {
            alertError("Niepoprawne id");
        }
    }

    @FXML
    public void backToWorkerMain(ActionEvent event) {
        sceneManager.show(SceneType.WORKER_MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
