package serwisPaczek.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Courier;
import serwisPaczek.model.User;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import serwisPaczek.repository.CourierRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourierCompaniesListController {

    private SceneManager sceneManager;

    @FXML
    private GridPane gridPane;

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    UserRepository userRepository;

    public void initialize() {
        List<Courier> listCouriers = courierRepository.findAll();

        int index = 0;
        int gridRow = 0;
        int gridCol = 0;
        Button button[];
        button = new Button[100];

        for (Courier courier : listCouriers) {
            if (index % 4 == 0) {
                gridRow++;
                gridCol = 0;
                gridPane.addRow(gridRow);
            }
            gridCol++;

            button[index] = new Button(courier.getName());
            button[index].setMinHeight(120);
            button[index].setMinWidth(135);
            button[index].setStyle(
                    "-fx-background-radius: 3px; " +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: darkslategrey;"
            );
            button[index].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sceneManager.show(SceneType.PRICE_LIST);
                }
            });
            gridPane.add(button[index], gridCol, gridRow);

            System.out.println("Grid [ROW]: " + gridRow);
            System.out.println("Grid [COL]: " + gridCol);
            System.out.println("Index [ID]: " + index);
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
