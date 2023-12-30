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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT bds.customer.customer_id, first_name, last_name, street, postal_code, house_num, street_num, telephone, email, city_name, country_name" +
                    " FROM  bds.customer" +
                    " JOIN bds.address ON bds.address.customer_id = bds.customer.customer_id" +
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

    public CustomerBasicView getCustomerInfo(String username) {
        String findCustomer = "SELECT first_name, last_name, c.customer_id, street, street_num, house_num, postal_code, email, telephone, city_name, country_name" +
                " FROM bds.customer c JOIN bds.address a ON a.customer_id = c.customer_id" +
                " JOIN bds.contact ct ON ct.customer_id = c.customer_id" +
                " JOIN bds.city ci ON ci.city_id = a.city_id" +
                " JOIN bds.country co ON co.country_id = a.country_id" +
                " WHERE c.username = ?";

        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findCustomer, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToCustomerBasicInfo(resultSet);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    private CustomerBasicView mapToCustomerBasicInfo(ResultSet rs) throws SQLException {
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
    public void insertName(String first_name, String last_name, String username){
        String insertNameSQL = "UPDATE bds.customer" + " SET first_name = ?, last_name = ?" + " WHERE bds.customer.username = ?;";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertNameSQL, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, username);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new DataAccessException("Creating person failed, no rows affected.");
            }

        }catch (SQLException e){
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }

    }

    public void insertAddress(String street, int streetNum, int postalCode, int houseNum, String city, String country, String username){
        String insertAdressSql = "WITH customer_info AS (" +
                "  SELECT customer_id FROM bds.customer" +
                "  WHERE username = ?" +
                ")," +
                "new_city AS (" +
                "  INSERT INTO bds.city (city_name)" +
                "  SELECT ? " + //city_name
                "  WHERE NOT EXISTS (SELECT 1 FROM bds.city WHERE city_name = ?)" + //city_name
                "  RETURNING city_id" +
                ")," +
                "new_country AS (" +
                "  INSERT INTO bds.country (country_name)" +
                "  SELECT ? " + //country_name
                "  WHERE NOT EXISTS (SELECT 1 FROM bds.country WHERE country_name = ?)" + //country_name
                "  RETURNING country_id" +
                ")" +
                "INSERT INTO bds.address (customer_id, city_id, country_id, street, street_num, house_num, postal_code)" +
                "SELECT" +
                "  (SELECT customer_id FROM customer_info)," +
                "  COALESCE((SELECT city_id FROM bds.city WHERE city_name = ?), (SELECT city_id FROM new_city))," + //city-name
                "  COALESCE((SELECT country_id FROM bds.country WHERE country_name = ?), (SELECT country_id FROM new_country))," + //country_name
                "  ?," + //street
                "  ?," + //streetNum
                "  ?," + //houseNum
                "  ?;"; //PSC
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAdressSql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, country);
            preparedStatement.setString(5, country);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, street);
            preparedStatement.setInt(9, streetNum);
            preparedStatement.setInt(10, houseNum);
            preparedStatement.setInt(11, postalCode);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new DataAccessException("Creating person failed, no rows affected.");
            }

        }catch (SQLException e){
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }


    public void insertContact(String email, int telephone, String username) {
        String insertContactSql = "INSERT INTO bds.contact (email, telephone, customer_id)" +
                " SELECT " +
                "    ? AS email," +
                "    ? AS telephone," +
                "    c.customer_id" +
                " FROM" +
                "    bds.customer c" +
                " WHERE" +
                "    c.username = ?;";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertContactSql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, telephone);
            preparedStatement.setString(3, username);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        }catch (SQLException e){
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }


    public SignInView findCustomerByUsername (String username){
        try(Connection connection = DataSourceConfig.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, password" + " FROM bds.customer" +  " WHERE username = ?")){
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
        String insertPersonSQL = "INSERT INTO bds.customer (username, password) VALUES (?,?)";
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
