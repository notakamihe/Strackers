package sample.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.CurrentUser;
import sample.DB;
import sample.TheStage;
import java.sql.*;
import javafx.event.ActionEvent;
import sample.models.User;
import javafx.scene.control.Label;

import java.security.SecureRandom;


public class AddStickerController {

    @FXML
    private TextField stickerNameTextField;

    @FXML
    private Button addStickerButton;

    @FXML
    private Button backButton;

    @FXML
    private Label errorLabel;
    
    public void initialize () {
        errorLabel.setVisible(false);
    }

    public void addSticker(ActionEvent actionEvent) throws Exception {
        System.out.println(stickerNameTextField.getText());

        if(stickerNameTextField.getText().equals(""))
        {
            errorLabel.setText("Name must not be blank.");
            errorLabel.setVisible(true);
            return;
        }

        errorLabel.setVisible(false);

        try {
            Statement statement = DB.connection.createStatement();
            String QUERY = String.format("INSERT INTO stickers (name, userID, location) VALUES ('%s', '%s', '%s')",
                    stickerNameTextField.getText(), CurrentUser.getUser().getId(), getRandomLocation());

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

    public String getRandomLocation()
    {
        SecureRandom rand = new SecureRandom();
        int randomNumber;
        int randomNumber2;
        char[] northSouth = {'N', 'S'};
        char[] eastWest = {'W', 'E'};
        char directionNS;
        char directionWE;

        randomNumber = rand.nextInt(89) + 1;
        randomNumber2 = rand.nextInt(89) + 1;

        directionNS = northSouth[rand.nextInt(2)];
        directionWE = eastWest[rand.nextInt(2)];

        return String.format("%d %c %d %c", randomNumber, directionNS, randomNumber2, directionWE);
    }


    public void goBack(ActionEvent actionEvent) throws Exception {
        TheStage.goBack();
    }
}
