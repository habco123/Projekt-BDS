package org.but.feec.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.but.feec.api.BookBasicView;
import org.but.feec.data.BookRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BookStoreController {

    @FXML
    private VBox bookListVBox;

    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        // Load book data from the database
        BookRepository bookRepository = new BookRepository();
        List<BookBasicView> books = bookRepository.getBookBasicView();

        // Populate the VBox with book names
        for (BookBasicView book : books) {
            addBookLabel(book);
        }
    }

    @FXML
    private void searchBooks() {
        String searchTerm = searchField.getText().toLowerCase();

        // Clear existing entries
        bookListVBox.getChildren().clear();

        // Load book data from the database
        BookRepository bookRepository = new BookRepository();
        List<BookBasicView> matchingBooks = bookRepository.getBookBasicView()
                .stream()
                .filter(book -> book.getBook_name().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        // Populate the VBox with matching book names
        for (BookBasicView book : matchingBooks) {
            addBookLabel(book);
        }
    }

    private void addBookLabel(BookBasicView book) {
        Label bookLabel = new Label(book.getBook_name());
        bookLabel.setOnMouseClicked(event -> showBookDetails(book));
        bookListVBox.getChildren().add(bookLabel);
    }

    private void showBookDetails(BookBasicView book) {
        try {
            // Load the FXML file for the book details window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/but/feec/fxml/BookDetails.fxml"));
            VBox bookDetails = loader.load();

            // Get the controller to set the book details
            BookDetailsController controller = loader.getController();
            controller.initialize(book);

            // Create a new stage for the book details window
            javafx.stage.Stage detailsStage = new javafx.stage.Stage();
            detailsStage.setTitle("Book Details");

            // Set the scene for the book details window
            javafx.scene.Scene scene = new javafx.scene.Scene(bookDetails);
            detailsStage.setScene(scene);

            // Show the book details window
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
