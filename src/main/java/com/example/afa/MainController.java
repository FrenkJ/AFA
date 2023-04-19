package com.example.afa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<TableInfo> platesTableView;
    @FXML
    private TableColumn<TableInfo , String> licensePlateCol;
    @FXML
    private TableColumn<TableInfo , String> OwnerFirst_NameCol;
    @FXML
    private TableColumn<TableInfo , String> OwnerLast_NameCol;
    @FXML
    private TableColumn<TableInfo , String> VehicleTypeCol;
    @FXML
    private TableColumn<TableInfo , String> VehicleYearCol;
    @FXML
    private TableColumn<TableInfo , String> hasFinesCol;
    @FXML
    private TextField searchBarField;

    ObservableList<TableInfo>tableInfoObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize (URL url, ResourceBundle resource){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String tableInfoQuery = "SELECT LicensePlate,OwnerFIrst_Name,OwnerLast_Name,vehicleType,VehicleYear,hasFine FROM licensePlates";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(tableInfoQuery);

            while  (queryOut.next()){
                String licensePlate = queryOut.getString("LicensePlate");
                String ownerFirst = queryOut.getString("OwnerFirst_Name");
                String ownerLast = queryOut.getString("OwnerLast_Name");
                String carType = queryOut.getString("VehicleType");
                String carYear = queryOut.getString("VehicleYear");
                String fines = queryOut.getString("hasFine");

                tableInfoObservableList.add(new TableInfo(licensePlate,ownerFirst,ownerLast,carType,carYear,fines));

                licensePlateCol.setCellValueFactory(new PropertyValueFactory<>("LicensePlate"));
                OwnerFirst_NameCol.setCellValueFactory(new PropertyValueFactory<>("OwnerFirst_Name"));
                OwnerLast_NameCol.setCellValueFactory(new PropertyValueFactory<>("OwnerLast_Name"));
                VehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("VehicleType"));
                VehicleYearCol.setCellValueFactory(new PropertyValueFactory<>("VehicleYear"));
                hasFinesCol.setCellValueFactory(new PropertyValueFactory<>("hasFine"));

                platesTableView.setItems(tableInfoObservableList);

                FilteredList<TableInfo>filteredData = new FilteredList<>(tableInfoObservableList,b -> true);

                searchBarField.textProperty().addListener((observable,oldValue,newValue) -> {
                    filteredData.setPredicate(TableInfo -> {

                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                            return  true;
                        }
                        String searchKeyword = newValue.toLowerCase();
                        if (TableInfo.getLicensePlate().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (TableInfo.getOwnerFirst_Name().toLowerCase().indexOf(searchKeyword)> -1) {
                            return true;
                        }else if (TableInfo.getOwnerLast_Name().toLowerCase().indexOf(searchKeyword)>-1){
                            return true;
                        } else if (TableInfo.getVehicleType().toLowerCase().indexOf(searchKeyword)> -1) {
                            return true;
                        } else if (TableInfo.getVehicleYear().toLowerCase().indexOf(searchKeyword)>-1) {
                            return true;
                        }else if (TableInfo.getHasFine().toLowerCase().indexOf(searchKeyword)>-1){
                            return true;
                        }else
                            return false;
                    });
                });
                SortedList<TableInfo>sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(platesTableView.comparatorProperty());
                platesTableView.setItems(sortedData);
            }

        }catch(SQLException e){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }


    }
// These methods will make scene communication possible
    public void AddFine (ActionEvent e)throws IOException {

        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("addFine.fxml"))));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void registerPlate(ActionEvent e)throws IOException{

        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("addPlate.fxml"))));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logoutButtonOnAction (ActionEvent e)throws IOException{

        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Login.fxml"))));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void fineInfoOnAction(ActionEvent e)throws IOException{

        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("FineInfo.fxml"))));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openInfoPage (ActionEvent e )throws IOException{

        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("InfoPage.fxml"))));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
