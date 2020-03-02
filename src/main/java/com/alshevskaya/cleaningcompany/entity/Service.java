package com.alshevskaya.cleaningcompany.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Service extends Entity {
    private int serviceId;
    private String serviceName;
    private BigDecimal priceItem;
    private double orderQuantity;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_HASH = 31;
    private static final int NUMBER_HASH_TWO = 32;

    public Service() {
    }

    public Service(int serviceId, String serviceName, BigDecimal priceItem, double orderQuantity) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.priceItem = priceItem;
        this.orderQuantity = orderQuantity;
    }

    public Service(String serviceName, BigDecimal priceItem, double orderQuantity) {
        this.serviceName = serviceName;
        this.priceItem = priceItem;
        this.orderQuantity = orderQuantity;
    }

    public Service(int serviceId, String serviceName, BigDecimal priceItem) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.priceItem = priceItem;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(BigDecimal priceItem) {
        this.priceItem = priceItem;
    }

    public double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Service service = (Service) o;

        if (serviceId != service.serviceId) return false;
        if (Double.compare(service.orderQuantity, orderQuantity) != 0) return false;
        if (serviceName != null ? !serviceName.equals(service.serviceName) : service.serviceName != null) return false;
        return priceItem != null ? priceItem.equals(service.priceItem) : service.priceItem == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serviceId;
        result = NUMBER_HASH * result + (serviceName != null ? serviceName.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (priceItem != null ? priceItem.hashCode() : NUMBER_ZERO);
        temp = Double.doubleToLongBits(orderQuantity);
        result = NUMBER_HASH * result + (int) (temp ^ (temp >>> NUMBER_HASH_TWO));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Service.class.getSimpleName() + "[", "]")
                .add("serviceId=" + serviceId)
                .add("serviceName='" + serviceName + "'")
                .add("priceItem=" + priceItem)
                .add("orderQuantity=" + orderQuantity)
                .toString();
    }
}
