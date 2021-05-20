package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.CurrentUser;
import sample.DB;
import sample.TheStage;
import sample.models.RegisterRegEx;
import sample.models.User;

import javax.xml.transform.Result;
import java.sql.*;

public class RegisterController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    public void initialize()
    {
        errorLabel.setVisible(false);
    }

    @FXML
    void goToLogin(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../UI/login.fxml"));
        Scene secondScene = new Scene(root);
        TheStage.setScene(secondScene);
    }

    @FXML
    void register(ActionEvent event) throws Exception {
        System.out.println("Registering...");
        System.out.printf("%s %s %s%n", usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText());

        if (!RegisterRegEx.isValidUsername(usernameTextField.getText()))
        {
            System.out.println("Invalid username");
            errorLabel.setText("Invalid username format. Must not be blank and must contain\nalphanumeric characters and/or underspaces.");
            errorLabel.setVisible(true);
            return;
        }
        else if (!RegisterRegEx.isValidEmail(emailTextField.getText()))
        {
            System.out.println("Invalid email");
            errorLabel.setVisible(true);
            errorLabel.setText("Invalid email format. Must not be blank and must include @ and . and either net org com or edu");
            return;
        } else if (!RegisterRegEx.isValidPassword(passwordTextField.getText())) {
            System.out.println("Invalid password");
            errorLabel.setText("Invalid password format. Cannot contain hashtags or special characters. \nMust not be blank and must contain between 8 and 20 characters.");
            errorLabel.setVisible(true);
            return;
        }

        errorLabel.setVisible(false);

        //Save to DB
        User newUser = new User(usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText());

        try {
            Statement statement = DB.connection.createStatement();
            String QUERY = String.format("INSERT INTO users (username, email, password) VALUES ('%s', '%s', '%s')",
                    newUser.getUsername(), newUser.getEmail(), newUser.getPassword());

            statement.execute(QUERY, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()){
                String id = rs.getString(1);
                newUser.setId(Integer.parseInt(id));
            }

            System.out.println(newUser.getId());

            CurrentUser.setUser(newUser);

            Parent root = FXMLLoader.load(getClass().getResource("../UI/stickerslist.fxml"));
            Scene scene = new Scene(root);
            TheStage.setScene(scene);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}