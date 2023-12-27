package org.but.feec.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookBasicView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty book_name = new SimpleStringProperty();
    private StringProperty genre = new SimpleStringProperty();
    private StringProperty publisher = new SimpleStringProperty();
    private StringProperty intro = new SimpleStringProperty();
    private StringProperty author_first_name = new SimpleStringProperty();
    private StringProperty author_last_name = new SimpleStringProperty();
    private LongProperty num_of_pages = new SimpleLongProperty();
    private LongProperty isbn = new SimpleLongProperty();
    private StringProperty release_year = new SimpleStringProperty();
    public long getId() {
        return id.get();
    }
    public void setId(long id) {
        this.id.set(id);
    }
    public LongProperty idProperty() {
        return id;
    }
    public String getBook_name() {
        return book_name.get();
    }
    public void setBook_name(String book_name) {
        this.book_name.set(book_name);
    }
    public StringProperty book_nameProperty() {
        return book_name;
    }
    public String getGenre() {
        return genre.get();
    }
    public void setGenre(String genre) {
        this.genre.set(genre);
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public String getPublisher() {
        return publisher.get();
    }
    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }
    public StringProperty publisherProperty() {
        return publisher;
    }
    public String getIntro() {
        return intro.get();
    }
    public void setIntro(String intro) {
        this.intro.set(intro);
    }
    public StringProperty introProperty() {
        return intro;
    }
    public String getAuthor_first_name() {
        return author_first_name.get();
    }
    public void setAuthor_first_name(String author_first_name) {
        this.author_first_name.set(author_first_name);
    }
    public StringProperty author_first_nameProperty() {
        return author_first_name;
    }
    public String getAuthor_last_name(){
        return author_last_name.get();
    }
    public void setAuthor_last_name(String author_last_name){
        this.author_last_name.set(author_last_name);
    }
    public StringProperty author_last_nameProperty(){
        return author_last_name;
    }
    public long getNum_of_pages() {
        return num_of_pages.get();
    }
    public void setNum_of_pages(long num_of_pages) {
        this.num_of_pages.set(num_of_pages);
    }
    public LongProperty num_of_pagesProperty() {
        return num_of_pages;
    }
    public long getIsbn() {
        return isbn.get();
    }
    public void setIsbn(long isbn) {
        this.isbn.set(isbn);
    }
    public LongProperty isbnProperty() {
        return isbn;
    }
    public String getRelease_year() {
        return release_year.get();
    }
    public void setRelease_year(String release_year) {
        this.release_year.set(release_year);
    }
    public StringProperty release_yearProperty() {
        return release_year;
    }
}
