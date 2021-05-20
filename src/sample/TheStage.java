package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TheStage {
    public static Stage currentStage;
    public static Scene previousScene;

    public static void setScene(Scene scene)
    {
        previousScene = currentStage.getScene();
        currentStage.setScene(scene);
    }

    public static void goBack ()
    {
        currentStage.setScene(previousScene);
    }
}
