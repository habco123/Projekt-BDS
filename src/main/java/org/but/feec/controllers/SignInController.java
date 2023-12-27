package org.but.feec.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void signInAction() {
        String id = idField.getText();
        String password = passwordField.getText();

        System.out.println("Signing in with ID: " + id + ", Password: " + password);
    }

    @FXML
    private void createAccountAction() {
        System.out.println("Create an account link clicked");
    }
}