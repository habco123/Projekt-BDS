package org.but.feec.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class OrderView {
    private LongProperty orderId = new SimpleLongProperty();
    private LongProperty customerId = new SimpleLongProperty();
    private StringProperty book_name = new SimpleStringProperty();
    private LongProperty price = new SimpleLongProperty();
    private StringProperty shipping = new SimpleStringProperty();
    private StringProperty orderStatus = new SimpleStringProperty();
    private StringProperty timeOfOrder = new SimpleStringProperty();


    public long getOrderId() {
        return orderId.get();
    }

    public void setOrderId(long orderId) {
        this.orderId.set(orderId);
    }

    public LongProperty orderIdProperty() {
        return orderId;
    }

    public String getTimeOfOrder() {
        return timeOfOrder.get();
    }

    public void setTimeOfOrder(String timeOfOrder) {
        this.timeOfOrder.set(timeOfOrder);
    }

    public StringProperty timeOfOrderProperty() {
        return timeOfOrder;
    }

    public long getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(long customerId) {
        this.customerId.set(customerId);
    }

    public LongProperty customerIdProperty() {
        return customerId;
    }


    public String getBookName() {
        return book_name.get();
    }

    public void setBookName(String bookName) {
        this.book_name.set(bookName);
    }

    public StringProperty bookNameProperty() {
        return book_name;
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
