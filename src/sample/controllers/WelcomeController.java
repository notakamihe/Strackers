package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.TheStage;

public class WelcomeController {

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;

    @FXML
    void goToLogIn(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../UI/login.fxml"));
        Scene scene = new Scene(root);
        TheStage.setScene(scene);
    }

    @FXML
    void goToSignUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../UI/register.fxml"));
        Scene scene = new Scene(root);
        TheStage.setScene(scene);
    }
}
