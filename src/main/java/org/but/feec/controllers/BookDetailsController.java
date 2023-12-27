package org.but.feec.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.but.feec.api.BookBasicView;

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
        // Populate the detailed information based on the selected book
        titleLabel.setText(book.getBook_name());
        genreLabel.setText("Genre: " + book.getGenre());
        publisherLabel.setText("Publisher: " + book.getPublisher());
        introLabel.setText("Introduction: " + book.getIntro());
        authorLabel.setText("Author: " + book.getAuthor_first_name() + " " + book.getAuthor_last_name());
        pagesLabel.setText("Number of Pages: " + book.getNum_of_pages());
        isbnLabel.setText("ISBN: " + book.getIsbn());
        releaseYearLabel.setText("Release Year: " + book.getRelease_year());
        // Set other properties based on the BookBasicView object
    }
}
