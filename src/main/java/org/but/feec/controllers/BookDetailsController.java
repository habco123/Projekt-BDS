package org.but.feec.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.api.BookBasicView;

import java.io.IOException;

public class BookDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Label introLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label pagesLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label releaseYearLabel;

    public void initialize(BookBasicView book) {
        titleLabel.setText(book.getBook_name());
        genreLabel.setText("Genre: " + book.getGenre());
        publisherLabel.setText("Publisher: " + book.getPublisher());
        introLabel.setText("Introduction: " + book.getIntro());
        authorLabel.setText("Author: " + book.getAuthor_first_name() + " " + book.getAuthor_last_name());
        pagesLabel.setText("Number of Pages: " + book.getNum_of_pages());
        isbnLabel.setText("ISBN: " + book.getIsbn());
        releaseYearLabel.setText("Release Year: " + book.getRelease_year());
    }

    @FXML
    private void handleBuyButton(ActionEvent event) {
        showPurchaseConfirmation();
    }

    private void showPurchaseConfirmation() {
        try {
            // Load the FXML file for the purchase confirmation window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/but/feec/fxml/PurchaseConfirmation.fxml"));
            VBox purchaseConfirmation = loader.load();

            // Create a new stage for the purchase confirmation window
            Stage purchaseConfirmationStage = new Stage();
            purchaseConfirmationStage.setTitle("Purchase Confirmation");

            Scene scene = new Scene(purchaseConfirmation);
            purchaseConfirmationStage.setScene(scene);

            purchaseConfirmationStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
