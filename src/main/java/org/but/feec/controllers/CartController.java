package org.but.feec.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.api.BookBasicView;

import java.awt.*;

public class CartController{

    @FXML
    private ListView<BookBasicView> cartListView;
    @FXML
    private Button buyButton;

    private ObservableList<BookBasicView> cartItems = FXCollections.observableArrayList();

    public void initialize() {
        cartListView.setItems(cartItems);
    }

    public void addToCart(BookBasicView book) {
        cartItems.add(book);
    }

    @FXML
    public void buyBooks() {

    }
}