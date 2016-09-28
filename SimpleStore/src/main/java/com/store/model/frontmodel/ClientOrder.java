package com.store.model.frontmodel;

import java.util.List;



public class ClientOrder {

    private Integer customerId;
    private String deliveryAddress;
    private Double exchange_rate;
    private List<ProductQuantity> orderDetail;



    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ProductQuantity> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<ProductQuantity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(Double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
