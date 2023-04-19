package com.example.afa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label registrationLabel;
    private Stage stage;
    private Scene scene;


    public void registerAccount (ActionEvent e) throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        try {
            PreparedStatement stmt = connectDB.prepareStatement("INSERT INTO useraccounts (Firstname,Lastname,Username,password) VALUES (?,?,?,?);");
            stmt.setString(1,firstNameField.getText());
            stmt.setString(2,lastNameField.getText());
            stmt.setString(3,usernameField.getText());
            stmt.setString(4,passwordField.getText());
            stmt.executeUpdate();
            stmt.close();
            connectDB.close();
            registrationLabel.setText("Successfully registered");
            if (registrationLabel.getText().equals("Successfully registered")){
                switchToLoginScene(e);
            }else {
                registrationLabel.setText("Something went wrong!");
            }
        }catch (SQLException exc){
            exc.printStackTrace();
        }
    }




    public void switchToLoginScene(ActionEvent event)throws IOException {
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Login.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
