package serwisPaczek;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import serwisPaczek.utils.SceneManager;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext springContext;


    @Autowired
    private SceneManager sceneManager;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager.init(primaryStage);
        primaryStage.setTitle("Serwis Paczek");
        primaryStage.setResizable(false);
    }

    @Override
    public void stop() {
        springContext.stop();
    }

}