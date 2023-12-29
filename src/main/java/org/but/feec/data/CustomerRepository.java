package org.but.feec.data;

import org.but.feec.api.*;
import org.but.feec.config.DataSourceConfig;
import org.but.feec.exceptions.DataAccessException;

import javax.xml.crypto.Data;
import java.nio.file.FileSystemNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomerRepository {


    public List<CustomerBasicView> getCustomerBasicView(){
        try(Connection connection = DataSourceConfig.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select first_name, last_name, street, postal_code, house_num, street_num, telephone, email, city_name, country_name from bds.customer JOIN bds.address ON bds.address.customer_id = bds.customer.customer_id" +
                    " JOIN bds.contact ON bds.contact.customer_id = bds.customer.customer_id" +
                    " JOIN bds.city ON bds.address.city_id = bds.city.city_id" +
                    " JOIN bds.country ON bds.address.country_id = bds.country.country_id;");
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

    public SignInView findCustomerByUsername (String username){
        try(Connection connection = DataSourceConfig.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, password" + " FROM bds.sign_in" +  " WHERE username = ?")){
            preparedStatement.setString(1,username);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return mapToCustomerAuth(resultSet);
                }
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return null;
    }

    public void  createPerson(UserCreateView userCreateView) {
        String insertPersonSQL = "INSERT INTO bds.sign_in (username, password) VALUES (?,?)";
        try (Connection connection = DataSourceConfig.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {


            preparedStatement.setString(1, userCreateView.getUsername());
            preparedStatement.setString(2, String.valueOf(userCreateView.getPassword()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }



    private SignInView mapToCustomerAuth(ResultSet rs) throws SQLException {
        SignInView signInView = new SignInView();
        signInView.setUsername(rs.getString("username"));
        signInView.setPassword(rs.getString("password"));

        return signInView;
    }


    private CustomerBasicView mapToCustomerBasicView(ResultSet rs) throws SQLException{
        CustomerBasicView customerBasicView = new CustomerBasicView();
        customerBasicView.setId(rs.getLong("customer_id"));
        customerBasicView.setFirst_name(rs.getString("first_name"));
        customerBasicView.setLast_name(rs.getString("last_name"));
        customerBasicView.setCity(rs.getString("city_name"));
        customerBasicView.setEmail(rs.getString("email"));
        customerBasicView.setCountry(rs.getString("country_name"));
        customerBasicView.setHouse_num(rs.getLong("house_num"));
        customerBasicView.setPostal_code(rs.getLong("postal_code"));
        customerBasicView.setStreet_num(rs.getLong("street_num"));
        customerBasicView.setTelephone(rs.getLong("telephone"));
        customerBasicView.setStreet(rs.getString("street"));

        return customerBasicView;
    }
}
