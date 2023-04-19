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

public class FineInfoController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<FineInfoModel> fineTableView;

    @FXML
    private TableColumn<FineInfoModel,Integer> ID_Col;
    @FXML
    private TableColumn<FineInfoModel,String> finedPlateCol;
    @FXML
    private TableColumn<FineInfoModel,String> ammountFinedCol;
    @FXML
    private TableColumn<FineInfoModel,String> finedReasonCol;
    @FXML
    private TableColumn<FineInfoModel,String> finedDateCol;



    public void switchToMainScene(ActionEvent event)throws IOException {
        Parent root =  FXMLLoader.load((Objects.requireNonNull(getClass().getResource("mainScene.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<FineInfoModel> fineInfoModelObservableList = FXCollections.observableArrayList();

    public void initialize (URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String SQL = "SELECT ID,Fined_Plate,ammountFined,fineReason,dateFined FROM fines";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(SQL);

            while   (queryOut.next()){
                Integer ID = ((queryOut.getInt("ID")));
                String finedPlate = queryOut.getString("Fined_Plate");
                String ammountFined = queryOut.getString("ammountFined");
                String fineReason = queryOut.getString("fineReason");
                String dateFined = queryOut.getString("dateFined");

                fineInfoModelObservableList.add(new FineInfoModel(ID,finedPlate,ammountFined,fineReason,dateFined));

                ID_Col.setCellValueFactory(new PropertyValueFactory<>("ID"));
                finedPlateCol.setCellValueFactory(new PropertyValueFactory<>("Fined_Plate"));
                ammountFinedCol.setCellValueFactory(new PropertyValueFactory<>("ammountFined"));
                finedReasonCol.setCellValueFactory(new PropertyValueFactory<>("fineReason"));
                finedDateCol.setCellValueFactory(new PropertyValueFactory<>("dateFined"));

                fineTableView.setItems(fineInfoModelObservableList);
                FilteredList<FineInfoModel> filteredData = new FilteredList<>(fineInfoModelObservableList, b -> true);

                searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                    filteredData.setPredicate(TableInfo -> {

                        if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                            return  true;
                        }
                        String searchKeyword = newValue.toLowerCase();
                        if (TableInfo.getID().toString().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (TableInfo.getFined_Plate().toLowerCase().indexOf(searchKeyword)> -1) {
                            return true;
                        }else if (TableInfo.getAmmountFined().toLowerCase().indexOf(searchKeyword)>-1){
                            return true;

                        } else if (TableInfo.getFineReason().toLowerCase().indexOf(searchKeyword)> -1) {
                            return true;

                        } else if (TableInfo.getDateFined().toLowerCase().indexOf(searchKeyword)>-1) {
                            return true;


                        }else

                            return false;


                    });
                });

                SortedList<FineInfoModel> sortedData = new SortedList<>(filteredData);

                sortedData.comparatorProperty().bind(fineTableView.comparatorProperty());
                fineTableView.setItems(sortedData);
            }



        }catch(SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

}
