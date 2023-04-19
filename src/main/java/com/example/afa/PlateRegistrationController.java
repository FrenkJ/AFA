package com.example.afa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class PlateRegistrationController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField vehicleYearField;
    @FXML
    private TextField fineFIeld;
    @FXML
    private TextField licensePlateTextField;
    @FXML
    private TextField vehicleTypeTextField;
    @FXML
    private TextField ownerFirstTextField;
    @FXML
    private TextField ownerLastTextField;
    @FXML
    private TextField vehicleColorTextField;

    public void plateRegistration(ActionEvent e) throws IOException {
        //Establishes a new connection to the database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //Handles the exception for the SQLException
        try {
            //Prepared Statement used to update the database
            PreparedStatement stmt = connectDB.prepareStatement(" INSERT INTO licenseplates (LicensePlate,VehicleType,OwnerFirst_Name,OwnerLast_Name,VehicleColor,VehicleYear,hasFine) " +
                    " VALUES(?,?,?,?,?,?,?) ");
            stmt.setString(1, licensePlateTextField.getText());
            stmt.setString(2, vehicleTypeTextField.getText());
            stmt.setString(3, ownerFirstTextField.getText());
            stmt.setString(4, ownerLastTextField.getText());
            stmt.setString(5, vehicleColorTextField.getText());
            stmt.setString(6, vehicleYearField.getText());
            stmt.setString(7,fineFIeld.getText());
            stmt.executeUpdate();
            //Switches back to main scene
            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //Closes the statement created
            stmt.close();
            //Closes the connection
            connectDB.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public void switchToMainScene(ActionEvent event)throws IOException{
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
