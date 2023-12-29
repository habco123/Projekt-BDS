package org.but.feec.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Button SignInButton;
    private SignInController signInController;

    @FXML
    public void initialize() {

        BookRepository bookRepository = new BookRepository();
        List<BookBasicView> books = bookRepository.getBookBasicView();


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


            BookDetailsController controller = loader.getController();
            controller.initialize(book);


            javafx.stage.Stage detailsStage = new javafx.stage.Stage();
            detailsStage.setTitle("Book Details");


            javafx.scene.Scene scene = new javafx.scene.Scene(bookDetails);
            detailsStage.setScene(scene);


            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void loadSignIn(ActionEvent actionEvent) {
        try {
                // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/but/feec/fxml/SignIn.fxml"));
            Parent root = loader.load();

            Stage signInStage = new Stage();
            signInStage.setTitle("Sign In");
            signInStage.setScene(new Scene(root));


            signInController = loader.getController();

            signInStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
