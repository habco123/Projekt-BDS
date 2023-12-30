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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.but.feec.api.CustomerBasicView;
import org.but.feec.data.CustomerRepository;
import org.but.feec.services.UserService;

public class AccController {

    @FXML
    private Label firstNameLabel, lastNameLabel, streetLabel, postalCodeLabel,
            houseNumLabel, streetNumLabel, telephoneLabel, emailLabel, cityLabel, countryLabel;
    private String username;
    @FXML
    private Button saveButton;


    public void setUsername(String username) {
        this.username = username;
        updateCustomerInfo();
    }
    @FXML
    private void updateCustomerInfo() {
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerBasicView customer = customerRepository.getCustomerInfo(username);


        if (customer != null) {
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
        } else {
            System.out.println("Data not found");
        }
    }

    @FXML
    private void showCart(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/but/feec/fxml/Cart.fxml"));
            Parent cartRoot = loader.load();

            Stage cartStage = new Stage();
            cartStage.setTitle("Your Cart");
            cartStage.initModality(Modality.APPLICATION_MODAL);
            cartStage.setScene(new Scene(cartRoot));

            CartController cartController = loader.getController();

            cartStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    @FXML
    private void insertData() {
        Stage contactStage = new Stage();

        // Create UI components for the new stage
        Label emailInputLabel = new Label("Email:");
        Label telephoneInputLabel = new Label("Telephone:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label streetLabel = new Label("Street:");
        Label streetNumLabel = new Label("Street Number:");
        Label houseNumLabel = new Label("House Number:");
        Label postalLabel = new Label("Postal Code:");
        Label cityLabel = new Label("City:");
        Label countryLabel = new Label("Country:");

        TextField emailTextField = new TextField();
        TextField telephoneTextField = new TextField();
        TextField first_nameTextField = new TextField();
        TextField last_nameTextField = new TextField();
        TextField streetTextField = new TextField();
        TextField streetNumTextField = new TextField();
        TextField houseNumTextField = new TextField();
        TextField postalTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField countryTextField = new TextField();

        Button saveButton = new Button("Save");

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(
                emailInputLabel, emailTextField,
                telephoneInputLabel, telephoneTextField,
                firstNameLabel, first_nameTextField,
                lastNameLabel, last_nameTextField,
                streetLabel, streetTextField,
                streetNumLabel, streetNumTextField,
                houseNumLabel, houseNumTextField,
                postalLabel, postalTextField,
                cityLabel, cityTextField,
                countryLabel, countryTextField,
                saveButton
        );

        vBox.setPadding(new javafx.geometry.Insets(10));

        saveButton.setOnAction(event -> {
            // Get the values from text fields
            String email = emailTextField.getText();
            String telephoneText = telephoneTextField.getText();
            String first_name = first_nameTextField.getText();
            String last_name = last_nameTextField.getText();
            String street = streetTextField.getText();
            String streetNumText = streetNumTextField.getText();
            String houseNumText = houseNumTextField.getText();
            String postalCodeText = postalTextField.getText();
            String city = cityTextField.getText();
            String country = countryTextField.getText();

            if (email.isEmpty() || telephoneText.isEmpty() || first_name.isEmpty() || last_name.isEmpty()
                    || street.isEmpty() || streetNumText.isEmpty() || houseNumText.isEmpty()
                    || postalCodeText.isEmpty() || city.isEmpty() || country.isEmpty()) {

                System.out.println("Please fill in all fields.");
            } else {
                CustomerRepository customerRepository = new CustomerRepository();

                int telephone = Integer.parseInt(telephoneText);
                int streetNum = Integer.parseInt(streetNumText);
                int houseNum = Integer.parseInt(houseNumText);
                int postalCode = Integer.parseInt(postalCodeText);

                customerRepository.insertName(first_name, last_name, username);
                customerRepository.insertContact(email, telephone, username);
                customerRepository.insertAddress(street, streetNum, houseNum, postalCode, city, country, username);

                contactStage.close();

                updateCustomerInfo();
            }


        });

        contactStage.setScene(new javafx.scene.Scene(vBox));
        contactStage.setTitle("Insert Contact Information");
        contactStage.show();
    }
}
