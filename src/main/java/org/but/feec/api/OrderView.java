package org.but.feec.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderView {
    private LongProperty customerId = new SimpleLongProperty();
    private StringProperty book_id = new SimpleStringProperty();
    private LongProperty price = new SimpleLongProperty();
    private StringProperty shipping = new SimpleStringProperty();
    private StringProperty orderStatus = new SimpleStringProperty();


    public long getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(long customerId) {
        this.customerId.set(customerId);
    }

    public LongProperty customerIdProperty() {
        return customerId;
    }


    public String getBookId() {
        return book_id.get();
    }

    public void setBookId(String bookId) {
        this.book_id.set(bookId);
    }

    public StringProperty bookIdProperty() {
        return book_id;
    }


    public long getPrice() {
        return price.get();
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    public LongProperty priceProperty() {
        return price;
    }


    public String getShipping() {
        return shipping.get();
    }

    public void setShipping(String shipping) {
        this.shipping.set(shipping);
    }

    public StringProperty shippingProperty() {
        return shipping;
    }


    public String getOrderStatus() {
        return orderStatus.get();
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus.set(orderStatus);
    }

    public StringProperty orderStatusProperty() {
        return orderStatus;
    }


}
