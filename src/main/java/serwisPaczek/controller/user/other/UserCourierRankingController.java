package serwisPaczek.controller.user.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.UserOrder;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;

@Controller
public class UserCourierRankingController {
    @Autowired
    OrderRepository orderRepository;
    private SceneManager sceneManager;
    @FXML
    private PieChart rankingPieChart;

    /**
     * This method is used for filling created pie chart with data
     */
    @FXML
    public void initialize() {
        List<UserOrder> orders = orderRepository.findAll();

        Map<String, Integer> courierCount = new HashMap<>();
        for (UserOrder order : orders) {
            Integer count = courierCount.get(order.getCourier().getName());
            courierCount.put(order.getCourier().getName(), (count == null) ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> entry : courierCount.entrySet()) {
            rankingPieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
    }

    @FXML
    public void openMainPanel(ActionEvent event) {
        if (getLoggedUser() == null) {
            sceneManager.show(SceneType.MAIN);
        } else {
            sceneManager.show(SceneType.USER_MAIN);
        }
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
