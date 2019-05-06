package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.CourierRepository;

import java.util.List;

@Controller
public class CourierCompaniesListController {
    private SceneManager sceneManager;
    private static long courierID;
    private static String courierName;
    @FXML
    private GridPane gridPane;
    @Autowired
    CourierRepository courierRepository;

    public static long getCourierID() {
        return courierID;
    }

    private static void setCourierID(long courierID) {
        CourierCompaniesListController.courierID = courierID;
    }

    public static String getCourierName() {
        return courierName;
    }

    private static void setCourierName(String courierName) {
        CourierCompaniesListController.courierName = courierName;
    }

    public void initialize() {
        List<Courier> listCouriers = courierRepository.findAll();
        int index = 0;
        int gridRow = 0;
        int gridCol = 0;
        String color = "";
        int numberOfButtons = (int) courierRepository.count();
        Button button[];
        button = new Button[numberOfButtons];

        for (Courier courier : listCouriers) {
            if (index % 4 == 0) {
                gridRow++;
                gridCol = 0;
                gridPane.addRow(gridRow);
            }
            // Btn color switcher
            switch (index % 4) {
                case (0):
                    color = "red";
                    break;
                case (1):
                    color = "orange";
                    break;
                case (2):
                    color = "olive";
                    break;
                case (3):
                    color = "indigo";
                    break;
            }
            button[index] = new Button(courier.getName());
            button[index].setMinHeight(120);
            button[index].setMinWidth(135);
            button[index].setStyle("-fx-background-radius: 3px; " +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: " + color + ";"
            );

            button[index].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setCourierID(courier.getId());
                    setCourierName(courier.getName());
                    sceneManager.show(SceneType.PRICE_LIST);
                }
            });
            gridPane.add(button[index], gridCol, gridRow);
            gridCol++;
            index++;
        }
    }

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
