package org.but.feec.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.but.feec.api.CustomerBasicView;
import org.but.feec.services.UserService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccController {
    @FXML
    private TableView<CustomerBasicView> tableView;
    private UserService userService;

    @FXML
    public void initialize() {
        List<CustomerBasicView> customerBasicViews = userService.getCustomerBasicView();

        tableView.getItems().addAll(customerBasicViews);
    }

}
