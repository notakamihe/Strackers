package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.CurrentUser;
import sample.DB;
import sample.TheStage;
import java.sql.*;
import sample.models.User;
import sample.models.RegisterRegEx;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label errorLabel;

    public void initialize()
    {
        errorLabel.setVisible(false);
    }

    @FXML
    void goToSignUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../UI/register.fxml"));
        Scene secondScene = new Scene(root);
        TheStage.setScene(secondScene);
    }

    @FXML
    void logIn(ActionEvent event) throws Exception {
        System.out.println("Logging In...");
        System.out.printf("%s %s%n", usernameTextField.getText(), passwordTextField.getText());

        if (!RegisterRegEx.isValidUsername(usernameTextField.getText()))
        {
            System.out.println("Invalid username");
            errorLabel.setText("Invalid username format. Must not be blank and must contain\nalphanumeric characters and/or underspaces.");
            errorLabel.setVisible(true);
            return;
        } else if (!RegisterRegEx.isValidPassword(passwordTextField.getText())) {
            System.out.println("Invalid password");
            errorLabel.setText("Invalid password format. Cannot contain hashtags or special characters. \nMust not be blank and must contain between 8 and 20 characters.");
            errorLabel.setVisible(true);
            return;
        }

        errorLabel.setVisible(false);

        User theUser = new User();

        try
        {
            Statement statement = DB.connection.createStatement();
            String QUERY = String.format("SELECT * FROM users WHERE username = '%s'", usernameTextField.getText());

            ResultSet resultSet = statement.executeQuery(QUERY);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int rowCount = 0;

            while (resultSet.next())
            {
                theUser.setId(Integer.parseInt(resultSet.getObject(1).toString()));
                theUser.setEmail(resultSet.getObject(3).toString());
                rowCount++;
                theUser = new User (
                        Integer.parseInt(resultSet.getObject(1).toString()),
                        resultSet.getObject(2).toString(),
                        resultSet.getObject(3).toString(),
                        resultSet.getObject(4).toString()
                );
            }

            if (rowCount == 0)
            {
                errorLabel.setText("The username entered was not found.");
                errorLabel.setVisible(true);
                return;
            }
            errorLabel.setVisible(false);

            if(!theUser.getPassword().equals(passwordTextField.getText()))
            {
                errorLabel.setText("Invalid password.");
                errorLabel.setVisible(true);
                return;
            }

            CurrentUser.setUser(theUser);

            System.out.println("Current user: " + CurrentUser.getUser().toString());

            Parent root = FXMLLoader.load(getClass().getResource("../UI/stickerslist.fxml"));
            Scene scene = new Scene(root);
            TheStage.setScene(scene);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
