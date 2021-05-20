package sample.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.CurrentSticker;
import sample.CurrentUser;
import sample.DB;
import sample.TheStage;
import javafx.event.ActionEvent;
import sample.models.Sticker;

import java.io.IOException;
import java.sql.*;

public class StickersListController {
    @FXML
    private Button logOutButton;

    @FXML
    private Button addStickerButton;

    @FXML
    private Button goButton;

    @FXML
    private VBox stickersListVBox;

    public void initialize () {
        System.out.println("Started.");

        try {

            Statement statement = DB.connection.createStatement();
            String QUERY = String.format("SELECT * FROM stickers WHERE userID = %d ORDER BY id DESC;",
                    CurrentUser.getUser().getId());

            ResultSet resultSet = statement.executeQuery(QUERY);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (resultSet.next()) {
                Sticker sticker = new Sticker(
                        Integer.parseInt(resultSet.getObject(1).toString()),
                        resultSet.getObject(2).toString(),
                        Integer.parseInt(resultSet.getObject(3).toString()),
                        resultSet.getObject(4).toString()
                );

                VBox stickerVBox = new VBox();
                HBox stickerHBox = new HBox();
                Label nameLabel = new Label(sticker.getName());
                Label locationLabel = new Label(sticker.getLocation());
                Button goButton = new Button("GO");

                nameLabel.setFont(Font.font(null, FontWeight.BOLD, 18));

                stickerVBox.getChildren().addAll(nameLabel, locationLabel);
                stickerVBox.setStyle("-fx-padding: 0 0 16 0;");
                HBox.setHgrow(stickerVBox, Priority.ALWAYS);

                goButton.setOnAction(ae -> {
                    try {
                        System.out.println("Going to detail....");
                        CurrentSticker.setSticker(sticker);

                        Parent root = FXMLLoader.load(getClass().getResource("../UI/stickerdetail.fxml"));
                        Scene scene = new Scene(root);
                        TheStage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                goButton.setStyle("-fx-background-color: #0fb2f8; -fx-text-fill: white");
                goButton.setFont(Font.font(null, FontWeight.BOLD, 14));

                stickerHBox.getChildren().addAll(stickerVBox, goButton);
                stickersListVBox.getChildren().add(stickerHBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Adding to scroll pane...");
    }

    @FXML
    void goToAddSticker(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../UI/addsticker.fxml"));
        Scene scene = new Scene(root);
        TheStage.setScene(scene);
    }

    @FXML
    void logOut(ActionEvent event) throws Exception{
        CurrentUser.removeUser();

        Parent root = FXMLLoader.load(getClass().getResource("../UI/login.fxml"));
        Scene scene = new Scene(root);
        TheStage.setScene(scene);
    }
}
