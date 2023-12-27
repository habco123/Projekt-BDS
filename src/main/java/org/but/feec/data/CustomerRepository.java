package org.but.feec.data;

import org.but.feec.api.*;
import org.but.feec.config.DataSourceConfig;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomerRepository {

    public List<CustomerBasicView> getCustomerBasicView(){
        try(Connection connection = DataSourceConfig.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT customer_id, first_name, last_name FROM bds.customer");
            ResultSet resultSet = preparedStatement.executeQuery();) {
            List<CustomerBasicView> customerBasicViews = new ArrayList<>();
            while(resultSet.next()){
                customerBasicViews.add(mapToCustomerBasicView(resultSet));
            }
            return customerBasicViews;
        }catch (SQLException e){
                System.out.println("Customers could not be loaded.----------");
                e.printStackTrace();
        }
        return null;
    }

    private CustomerBasicView mapToCustomerBasicView(ResultSet rs) throws SQLException{
        CustomerBasicView customerBasicView = new CustomerBasicView();
        customerBasicView.setId(rs.getLong("customer_id"));
        customerBasicView.setFirst_name(rs.getString("first_name"));
        customerBasicView.setLast_name(rs.getString("last_name"));
        return customerBasicView;

    }






}
