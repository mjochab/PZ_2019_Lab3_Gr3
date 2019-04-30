package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.repository.OrderRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.util.*;

@Controller
public class CourierRankingController {
    private SceneManager sceneManager;

    @Autowired
    OrderRepository orderRepository;



    @FXML
    private PieChart rankingPieChart;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        List<UserOrder> orders = orderRepository.findAll();
//        System.out.println(orderRepository.count());

        Map<String, Integer> courierCount = new HashMap<>();
        for (UserOrder order : orders) {
            Integer count = courierCount.get(order.getCourier().getName());
            courierCount.put(order.getCourier().getName(), (count == null) ? 1 : count + 1);
        }

        for(Map.Entry<String,Integer> entry : courierCount.entrySet()){
            rankingPieChart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
//            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }
}
