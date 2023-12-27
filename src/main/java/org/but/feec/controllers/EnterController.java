package org.but.feec.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EnterController {

    @FXML
    private Button enterButton;

    @FXML
    private void handleEnterButton(ActionEvent event) {
        try {
            // Load the FXML file for the next window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/but/feec/fxml/BookStore.fxml"));
            Parent nextWindow = loader.load();

            // Create a new stage for the next window
            Stage nextStage = new Stage();
            nextStage.setTitle("BookStore");

            // Set the scene for the next window
            Scene scene = new Scene(nextWindow);
            nextStage.setScene(scene);

            // Show the next window
            nextStage.show();

            // Close the current window (if needed)
            Stage currentStage = (Stage) enterButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
