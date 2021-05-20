package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Window;
import sample.CurrentSticker;
import sample.CurrentUser;
import sample.DB;
import sample.TheStage;
import java.sql.*;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert;
import javafx.application.Platform;

public class StickerDetailComponent {

    @FXML
    private Button backButton;

    @FXML
    private Label stickerNameLabel;

    @FXML
    private Label stickerLocationLabel;

    @FXML
    private Button showLightButton;

    @FXML
    private Button playSoundButton;

    @FXML
    private Button removeButton;

    public void initialize () {
        System.out.println(CurrentSticker.
                getSticker().toString());

        stickerNameLabel.setText(CurrentSticker.getSticker().getName());
        stickerLocationLabel.setText(CurrentSticker.getSticker().getLocation());
    }

    public void goBack(ActionEvent actionEvent) {
        System.out.println("Going back...");
        TheStage.goBack();
    }

    public void showLight(ActionEvent actionEvent) {
        System.out.println("Showing Light");

        Alert alert = new Alert(AlertType.NONE);
        String msg = String.format("Showing light for '%s' at %s",
                CurrentSticker.getSticker().getName(), CurrentSticker.getSticker().getLocation());
        alert.setContentText(msg);
        alert.setTitle("Light");
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.showAndWait();
    }

    public void playSound(ActionEvent actionEvent) {
        System.out.println("Playing Sound");


        Alert alert = new Alert(AlertType.NONE);
        String msg = String.format("Playing sound for '%s' at %s", CurrentSticker.getSticker().getName(), CurrentSticker.getSticker().getLocation());
        alert.setTitle("Sound");
        alert.setContentText(msg);

        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.showAndWait();
    }

    public void remove(ActionEvent actionEvent) throws Exception {
        System.out.println("Sticker successfully removed");

        try {
            Statement statement = DB.connection.createStatement();
            String QUERY = String.format("DELETE FROM stickers WHERE id = '%d'", CurrentSticker.getSticker().getId());

            statement.execute(QUERY, Statement.RETURN_GENERATED_KEYS);

            Parent root = FXMLLoader.load(getClass().getResource("../UI/stickerslist.fxml"));
            Scene scene = new Scene(root);
            TheStage.setScene(scene);
        } catch (SQLException e) {
            {
                e.printStackTrace();
            }
        }
    }
}
