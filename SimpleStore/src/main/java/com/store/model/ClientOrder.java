package com.store.model;

import java.sql.Date;

public class ClientOrder {


    private Integer order_id;
    private Integer customer_Id;
    private String deliveryAddress;
    private Double exchange_rate;
    private Date date;


    public Integer getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(Integer customer_Id) {
        this.customer_Id = customer_Id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    public void setDate(Date date) {
        this.date = date;
    }
}
