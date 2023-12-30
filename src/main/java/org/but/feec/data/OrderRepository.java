package org.but.feec.data;

import org.but.feec.api.OrderView;
import org.but.feec.config.DataSourceConfig;
import org.but.feec.exceptions.DataAccessException;

import java.sql.*;

public class OrderRepository {

    public void insertOrder(String username, Long book_id, int shipping){
        String insertSQL = "WITH customer_data AS (" +
                "    SELECT" +
                "        customer_id" +
                "    FROM" +
                "        bds.customer" +
                "    WHERE" +
                "        username = ?" +//username
                ")," +
                "address_data AS (" +
                "    SELECT" +
                "        address_id" +
                "    FROM" +
                "        bds.address" +
                "    WHERE" +
                "        customer_id = (SELECT customer_id FROM customer_data)" +
                ")" +
                "INSERT INTO bds.order (" +
                "    customer_id," +
                "    dest_addres_id," +
                "    book_id," +
                "    shipping_method_id," +
                "    price," +
                "    time_of_order" +
                ")" +
                "VALUES (" +
                "    (SELECT customer_id FROM customer_data)," +
                "    (SELECT address_id FROM address_data)," +
                "    ?," + //book
                "    ?," + //shipping
                "    (" +
                "        SELECT" +
                "            COALESCE(shipping_cost, 0) + COALESCE(price, 0)" +
                "        FROM" +
                "            bds.shipping" +
                "            CROSS JOIN bds.book" +
                "        WHERE" +
                "            shipping_method_id = ?" +
                "            AND book_id = ?" +
                "    )," +
                "    CURRENT_TIMESTAMP" +
                ");";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, book_id);
            preparedStatement.setInt(3, shipping);
            preparedStatement.setInt(4, shipping);
            preparedStatement.setLong(5, book_id);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        }catch (SQLException e){
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

}
