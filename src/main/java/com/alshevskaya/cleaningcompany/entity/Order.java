package com.alshevskaya.cleaningcompany.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Order extends Entity {
    private int orderId;
    private LocalDateTime dateOrder;
    private BigDecimal price;
    private int clientId;
    private int cleanerId;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_HASH = 31;

    public Order() {
    }

    public Order(int orderId, LocalDateTime dateOrder, BigDecimal price, int clientId, int cleanerId) {
        this.orderId = orderId;
        this.dateOrder = dateOrder;
        this.price = price;
        this.clientId = clientId;
        this.cleanerId = cleanerId;
    }

    public Order(LocalDateTime dateOrder, BigDecimal price, int clientId, int cleanerId) {
        this.dateOrder = dateOrder;
        this.price = price;
        this.clientId = clientId;
        this.cleanerId = cleanerId;
    }

    public Order(int orderId, LocalDateTime dateOrder, BigDecimal price, int clientId) {
        this.orderId = orderId;
        this.dateOrder = dateOrder;
        this.price = price;
        this.clientId = clientId;
    }

    public Order(int orderId, int cleanerId, LocalDateTime dateOrder, BigDecimal price) {
        this.orderId = orderId;
        this.dateOrder = dateOrder;
        this.price = price;
        this.cleanerId = cleanerId;
    }

    public Order(int orderId, LocalDateTime dateOrder, BigDecimal price) {
        this.orderId = orderId;
        this.dateOrder = dateOrder;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (clientId != order.clientId) return false;
        if (cleanerId != order.cleanerId) return false;
        if (dateOrder != null ? !dateOrder.equals(order.dateOrder) : order.dateOrder != null) return false;
        return price != null ? price.equals(order.price) : order.price == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = NUMBER_HASH * result + (dateOrder != null ? dateOrder.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (price != null ? price.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + clientId;
        result = NUMBER_HASH * result + cleanerId;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("dateOrder=" + dateOrder)
                .add("price=" + price)
                .add("clientId=" + clientId)
                .add("cleanerId=" + cleanerId)
                .toString();
    }
}
