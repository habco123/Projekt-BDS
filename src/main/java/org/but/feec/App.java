package org.but.feec;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.exceptions.ExceptionHandler;


import javax.swing.*;

public class App extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
            VBox mainStage = loader.load();

            primaryStage.setTitle("Enter Screen");
            Scene scene = new Scene(mainStage);
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (Exception ex){
            ExceptionHandler.handleException(ex);
        }
    }
}
