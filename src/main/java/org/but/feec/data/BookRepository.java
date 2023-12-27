package org.but.feec.data;

import org.but.feec.api.BookBasicView;
import org.but.feec.api.CustomerBasicView;
import org.but.feec.config.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public List<BookBasicView> getBookBasicView() {
        try (Connection connection = DataSourceConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                     "    b.book_id, " +
                     "    b.book_name, " +
                     "    a.first_name," +
                     "    a.last_name, " +
                     "    g.genre_name, " +
                     "    p.publisher_name, " +
                     "    b.isbn, " +
                     "    b.release_year, " +
                     "    b.intro, " +
                     "    b.num_pages, " +
                     "    bq.num_of_book " +
                     "FROM " +
                     "    bds.book_has_author bha " +
                     "JOIN " +
                     "    bds.book b ON bha.book_id = b.book_id " +
                     "JOIN " +
                     "    bds.author a ON bha.author_id = a.author_id " +
                     "JOIN " +
                     "    bds.genre g ON b.genre_id = g.genre_id " +
                     "JOIN " +
                     "    bds.publisher p ON b.publisher_id = p.publisher_id " +
                     "JOIN " +
                     "    bds.book_quantity bq ON b.book_quantity_id = bq.book_quantity_id;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<BookBasicView> bookBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                bookBasicViews.add(mapToBookBasicView(resultSet));
            }
            return bookBasicViews;
        } catch (SQLException e) {
            System.out.println("Nemozno nacitat book.");
            e.printStackTrace();
        }
        return null;
    }

    private BookBasicView mapToBookBasicView(ResultSet rs) throws SQLException{
        BookBasicView bookBasicView = new BookBasicView();
        bookBasicView.setId(rs.getLong("book_id"));
        bookBasicView.setBook_name(rs.getString("book_name"));
        bookBasicView.setIsbn(rs.getLong("isbn"));
        bookBasicView.setAuthor_first_name(rs.getString("first_name"));
        bookBasicView.setAuthor_last_name(rs.getString("last_name"));
        bookBasicView.setGenre(rs.getString("genre_name"));
        bookBasicView.setIntro(rs.getString("intro"));
        bookBasicView.setPublisher(rs.getString("publisher_name"));
        bookBasicView.setRelease_year(rs.getString("release_year"));
        bookBasicView.setNum_of_pages(rs.getInt("num_pages"));
        return bookBasicView;
    }

}


