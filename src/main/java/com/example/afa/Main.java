package com.example.afa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;



public class Main extends Application {

    @Override
    public void start(Stage loginStage) throws IOException{

        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setScene(new Scene(root, 700, 500));
        loginStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}