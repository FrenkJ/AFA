package com.example.afa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class AddFineController {
    @FXML
    private Label registrationLabel;
    @FXML
    private TextField licensePlateField;
    @FXML
    private TextField fineReasonField;
    @FXML
    private TextField ammountFinedField;
    @FXML
    private DatePicker datePickerField;
    private Stage stage;
    private Scene scene;
    public void switchToMainScene(ActionEvent event)throws IOException {
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void registerFine (ActionEvent e ) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        PreparedStatement stmt = connectDB.prepareStatement("INSERT INTO fines (Fined_Plate,ammountFined,fineReason,dateFined) VALUES (?,?,?,?)");
        PreparedStatement stmt2 = connectDB.prepareStatement("UPDATE licenseplates SET hasFine = ? WHERE hasFine ='---'");
        stmt.setString(1,licensePlateField.getText());
        stmt.setString(2,ammountFinedField.getText());
        stmt2.setString(1,ammountFinedField.getText());
        stmt.setString(3,fineReasonField.getText());
        LocalDate date = datePickerField.getValue();
        stmt.setString(4,date.toString());
        stmt.executeUpdate();
        stmt2.executeUpdate();
        connectDB.close();
        registrationLabel.setText("Successfully registered!");

    }

}
