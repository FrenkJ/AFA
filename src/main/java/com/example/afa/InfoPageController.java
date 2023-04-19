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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class InfoPageController {
    @FXML
    private Label firstNameField;
    @FXML
    private Label lastNameField;
    @FXML
    private Label vehicleTypeFIeld;
    @FXML
    private Label vehicleColor;
    @FXML
    private Label vehicleYear;
    @FXML
    private Label fineLabel;
    @FXML
    private Label licensePlateField;
    @FXML
    private TextField plateTextField;
    private Stage stage;
    private Scene scene;

    public void returnInfo (ActionEvent e )  {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String sql = "SELECT LicensePlate,OwnerFirst_Name,OwnerLast_Name,vehicleType,VehicleColor,VehicleYear,hasFine " +
                "FROM licensePlates WHERE LicensePlate = '" + plateTextField.getText() + "'";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryRes = statement.executeQuery(sql);

            while   (queryRes.next()) {
                firstNameField.setText(queryRes.getString("OwnerFirst_Name"));
                lastNameField.setText(queryRes.getString("OwnerLast_Name"));
                licensePlateField.setText(queryRes.getString("LicensePlate"));
                vehicleTypeFIeld.setText(queryRes.getString("VehicleType"));
                vehicleColor.setText(queryRes.getString("vehicleColor"));
                vehicleYear.setText(queryRes.getString("VehicleYear"));
                fineLabel.setText(queryRes.getString("hasFine"));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }
    public void switchToMainScene(ActionEvent event)throws IOException {
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
