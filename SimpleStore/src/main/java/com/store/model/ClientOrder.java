package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    private Customer customer;
    private String deliveryAddress;
    private Double total;
    private Date dateOrder;
    @OneToMany(mappedBy = "clientOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // guardar tambien los detalles
    @JsonManagedReference
    private List<OrderDetail> orderDetail;

    protected ClientOrder() {
    }

    public ClientOrder(Customer customer, String deliveryAddress, Double total, Date date) {
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.dateOrder = date;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "ClientOrder{" + "customer=" + customer + ", deliveryAddress=" + deliveryAddress + ", total=" + total + ", dateOrder=" + dateOrder + ", orderDetail=" + orderDetail + '}';
    }
    
   
}
