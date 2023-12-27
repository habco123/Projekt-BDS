package org.but.feec.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class CustomerBasicView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty first_name = new SimpleStringProperty();
    private StringProperty last_name = new SimpleStringProperty();

    public long getId(){return idProperty().get(); }
    public void setId(Long id) {this.idProperty().setValue(id);}
    public String getFirst_name() {return first_nameProperty().get() ;}
    public void setFirst_name(String first_name){this.first_nameProperty().setValue(first_name); }

    public String getLast_name() {return last_nameProperty().get() ;}
    public void setLast_name(String last_name){this.last_nameProperty().setValue(last_name); }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty first_nameProperty() { return first_name; }
    public StringProperty last_nameProperty() {return last_name; }




}
