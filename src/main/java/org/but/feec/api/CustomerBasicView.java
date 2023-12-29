package org.but.feec.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class CustomerBasicView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty first_name = new SimpleStringProperty();
    private StringProperty last_name = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private LongProperty house_num = new SimpleLongProperty();
    private StringProperty street = new SimpleStringProperty();
    private LongProperty street_num = new SimpleLongProperty();
    private LongProperty postal_code = new SimpleLongProperty();
    private LongProperty telephone = new SimpleLongProperty();
    private StringProperty email = new SimpleStringProperty();

    public long getId() {
        return idProperty().get();
    }

    public void setId(long id) {
        this.idProperty().set(id);
    }
    public LongProperty idProperty() {
        return id;
    }
    public String getFirst_name() {
        return first_nameProperty().get();
    }
    public void setFirst_name(String first_name) {
        this.first_nameProperty().set(first_name);
    }
    public StringProperty first_nameProperty() {
        return first_name;
    }
    public String getLast_name() {
        return last_nameProperty().get();
    }
    public void setLast_name(String last_name) {
        this.last_nameProperty().set(last_name);
    }
    public StringProperty last_nameProperty() {
        return last_name;
    }
    public String getCity() {
        return cityProperty().get();
    }
    public void setCity(String city) {
        this.cityProperty().set(city);
    }
    public StringProperty cityProperty() {
        return city;
    }

    public String getCountry() {
        return countryProperty().get();
    }
    public void setCountry(String country) {
        this.countryProperty().set(country);
    }
    public StringProperty countryProperty() {
        return country;
    }
    public long getHouse_num() {
        return house_numProperty().get();
    }
    public void setHouse_num(long house_num) {
        this.house_numProperty().set(house_num);
    }
    public LongProperty house_numProperty() {
        return house_num;
    }
    public String getStreet() {
        return streetProperty().get();
    }
    public void setStreet(String street) {
        this.streetProperty().set(street);
    }
    public StringProperty streetProperty() {
        return street;
    }
    public long getStreet_num() {
        return street_numProperty().get();
    }
    public void setStreet_num(long street_num) {
        this.street_numProperty().set(street_num);
    }
    public LongProperty street_numProperty() {
        return street_num;
    }
    public long getPostal_code() {
        return postal_codeProperty().get();
    }
    public void setPostal_code(long postal_code) {
        this.postal_codeProperty().set(postal_code);
    }
    public LongProperty postal_codeProperty() {
        return postal_code;
    }
    public long getTelephone() {
        return telephoneProperty().get();
    }
    public void setTelephone(long telephone) {
        this.telephoneProperty().set(telephone);
    }
    public LongProperty telephoneProperty() {
        return telephone;
    }
    public String getEmail() {
        return emailProperty().get();
    }
    public void setEmail(String email) {
        this.emailProperty().set(email);
    }
    public StringProperty emailProperty() {
        return email;
    }

}
