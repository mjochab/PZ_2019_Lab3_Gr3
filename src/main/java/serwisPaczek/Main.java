package main.java.serwisPaczek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("pl"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainPage.fxml"));

        BorderPane borderPane = loader.load();


        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Serwis paczek");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
