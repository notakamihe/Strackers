package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/welcome.fxml"));
        Scene scene = new Scene(root);
        TheStage.currentStage = stage;

        TheStage.currentStage.setTitle("Strackers");
        TheStage.setScene(scene);
        TheStage.currentStage.show();

        DB.connect();

        try {
            Statement statement = DB.connection.createStatement();
            String QUERY = "SELECT * FROM users;";

            ResultSet resultSet = statement.executeQuery(QUERY);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i<=numColumns; i++)
                    System.out.printf ("%-8s\t", resultSet.getObject(i));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
