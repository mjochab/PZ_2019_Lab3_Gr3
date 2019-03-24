package serwisPaczek.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import serwisPaczek.utils.SceneType;


@Component
public class StageConfig {

    private String applicationTitle;
    private SceneType view;
    private Double width;
    private Double height;


    public StageConfig(@Value("${stage.applicationTitle:stage}") String applicationTitle,
                       @Value("${stage.view:MAIN}") SceneType view,
                       @Value("${stage.width:800}") Double width,
                       @Value("${stage.height:600}") Double height) {
        this.applicationTitle = applicationTitle;
        this.view = view;
        this.width = width;
        this.height = height;

    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public SceneType getView() {
        return view;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }


}