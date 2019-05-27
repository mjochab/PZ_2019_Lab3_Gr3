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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

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
    private TextField orderSearchText;

    public void initialize() {
        fillListViewWithAllOrders();
        statusComboBox.getItems().setAll(Status.values());
        statusComboBox.getSelectionModel().selectFirst();


    }

    private void fillListWithOrder(UserOrder order) {
        workerOrdersList.getItems().add(order.getId().toString() + "  |  " +
                order.getUser().getUsername() + "  " +
                order.getCourier().getName() + "  " +
                order.getPrice() + " zł  " +
                order.getStatus().name() + "    " +
                order.getDate().toString());
    }


    private void fillListViewWithAllOrders() {
        workerOrdersList.getItems().clear();
        List<UserOrder> orders = orderRepository.findAll();

        for (UserOrder order : orders) {
            fillListWithOrder(order);
        }
    }

    @FXML
    public void orderSearch(ActionEvent event) {
        if (
                orderSearchText.getText().matches("\\d+")) {
            try {
                UserOrder order = orderRepository.findById(Long.valueOf(orderSearchText.getText())).orElse(null);
                workerOrdersList.getItems().clear();
                fillListWithOrder(order);
            } catch (Exception e) {
                alertError("Nie znaleziono zamówienia o takim id");
            }
        } else {
            alertError("Niepoprawne id");
        }
    }

    @FXML
    public void orderChangeStatus(ActionEvent event) {
        try {
            String selectedItem = workerOrdersList.getSelectionModel().getSelectedItem();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(selectedItem);
            Long id = null;
            if (m.find()) {
                id = Long.valueOf(m.group(0));
            }

            // Changing order status
            UserOrder order = orderRepository.getOne(id);
            order.setStatus(statusComboBox.getValue());
            orderRepository.save(order);
            workerOrdersList.getItems().clear();
            fillListWithOrder(order);
        } catch (Exception e) {
            alertError("Niepoprawne id");
        }
    }


    @FXML
    public void resetSearch(ActionEvent event) {
        fillListViewWithAllOrders();
    }

    private void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                message, ButtonType.OK);
        alert.setTitle("Error");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        if (getLoggedUser().getRole().getId() == 1) {
            sceneManager.show(SceneType.ADMIN_MAIN);
        } else {
            sceneManager.show(SceneType.WORKER_MAIN);
        }
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
