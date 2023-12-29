package org.but.feec.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.api.CustomerBasicView;
import org.but.feec.data.CustomerRepository;
import javafx.scene.control.TextField;


public class AccController {


    @FXML
    private Label firstNameLabel, lastNameLabel, streetLabel, postalCodeLabel,
            houseNumLabel, streetNumLabel, telephoneLabel, emailLabel, cityLabel, countryLabel;

    private String username;



    // Method to set the username from SignInController
    public void setUsername(String username) {
        this.username = username;
        updateCustomerInfo();
    }

    private void updateCustomerInfo() {
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerBasicView customer = customerRepository.getCustomerInfo(username);

        if(customer != null) {
            firstNameLabel.setText("First Name: " + customer.getFirst_name());
            lastNameLabel.setText("Last Name: " + customer.getLast_name());
            streetLabel.setText("Street: " + customer.getStreet());
            postalCodeLabel.setText("Postal Code: " + String.valueOf(customer.getPostal_code()));
            houseNumLabel.setText("House Num: " + String.valueOf(customer.getHouse_num()));
            streetNumLabel.setText("Street Num: " + String.valueOf(customer.getStreet_num()));
            telephoneLabel.setText("Telephone: " + String.valueOf(customer.getTelephone()));
            emailLabel.setText("Email: " + customer.getEmail());
            cityLabel.setText("City: " + customer.getCity());
            countryLabel.setText("Country: " + customer.getCountry());
        }else
            System.out.println("Data not found");

    }

    @FXML
    private void insertData() {

        Stage contactStage = new Stage();

        // Create UI components for the new stage
        Label emailInputLabel = new Label("Email:");
        Label telephoneInputLabel = new Label("Telephone:");
        Label firstNameLabel = new Label("first_name");
        Label lastNameLabel = new Label("last_name");

        TextField emailTextField = new TextField();
        TextField telephoneTextField = new TextField();
        TextField first_nameTextField = new TextField();
        TextField last_nameTextField = new TextField();

        Button saveButton = new Button("Save");


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(emailInputLabel, emailTextField, telephoneInputLabel, telephoneTextField,  firstNameLabel, first_nameTextField, lastNameLabel, last_nameTextField, saveButton);
        vBox.setPadding(new javafx.geometry.Insets(10));

        // Set the action for the Save button
        saveButton.setOnAction(event -> {
            // Get the values from text fields
            String email = emailTextField.getText();
            String telephoneText = telephoneTextField.getText();
            String first_name = first_nameTextField.getText();
            String last_name = last_nameTextField.getText();
            CustomerRepository customerRepository = new CustomerRepository();

            int telephone = Integer.parseInt(telephoneText);

            customerRepository.insertName(first_name, last_name, username);
            customerRepository.insertContact(email, telephone, username);

            // Close the stage after saving
            contactStage.close();
        });

        // Set the scene and show the stage
        contactStage.setScene(new javafx.scene.Scene(vBox));
        contactStage.setTitle("Insert Contact Information");
        contactStage.show();
    }



}

