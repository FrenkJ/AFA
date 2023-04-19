package com.example.afa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;


import java.io.IOException;
import java.sql.*;
import java.util.Objects;



public class Controller {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label loginMessageLabel;
    public void loginButtonOnAction(ActionEvent e) throws IOException {

        if (usernameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {

            loginMessageLabel.setText("Please enter Username and password");
        }else {
            validateLogin();
            if (loginMessageLabel.getText().equals("Welcome")) {
                switchToMainScene(e);

            }
        }


    }

    public void setCancelButtonAction (ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
        public void validateLogin() {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String verifyLogin = "SELECT count(1) FROM useraccounts WHERE Username = '"+usernameTextField.getText()+"' AND password = '" +passwordTextField.getText()+"'";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult  = statement.executeQuery(verifyLogin);
                while   (queryResult.next()){
                    if (queryResult.getInt(1)==1){
                        loginMessageLabel.setText("Welcome");

                    }else {
                        loginMessageLabel.setText("Incorrect username or password");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    public void switchToMainScene(ActionEvent event)throws IOException{
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void OpenRegister(ActionEvent event)throws IOException{
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Register.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}